package wendland.michal.photogallery.controller

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import wendland.michal.photogallery.BuildConfig
import wendland.michal.photogallery.helper.CustomLogger
import wendland.michal.photogallery.listener.INewPhotoListener

class CameraController(
    private val fragment: Fragment,
    private val newPhotoListener: INewPhotoListener) {
    private var imageUri: Uri? = null
    private var absolutePath: String = ""

    fun launch(): Boolean {
        CustomLogger.logMethod()
        val context: Context? = fragment.context

        val hasCamera: Boolean =
            context?.packageManager?.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY) == true
        return if (hasCamera) {
            if (context != null) {
                ExternalStorageController.createImageFile(context)?.also {
                    imageUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", it)
                    absolutePath = it.absolutePath
                }
            }
            CustomLogger.d("imageUri: ${imageUri.toString()}")
            imageCaptureAction.launch(imageUri)
            true
        } else {
            CustomLogger.w("Camera is not detected")
            false
        }
    }

    private val imageCaptureAction = fragment.registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        CustomLogger.logMethod()

        CustomLogger.d("isSuccess: $isSuccess")
        if (isSuccess) {
            imageUri?.let { it -> newPhotoListener.onTakeNewPhoto(it, absolutePath) }
        }
    }

}