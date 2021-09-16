package wendland.michal.photogallery.controller

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import wendland.michal.photogallery.helper.CustomLogger

class CameraController(private val appCompatActivity: AppCompatActivity) {

    val image: Bitmap
        get() {
            return image
        }

    fun launch(): Boolean {
        CustomLogger().logMethod()

        var hasCamera: Boolean = appCompatActivity.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
        return if (hasCamera) {
            imageCaptureAction.launch(null)
            true
        } else {
            CustomLogger().w("Camera is not detected")
            false
        }
    }

    private val imageCaptureAction = appCompatActivity.registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        CustomLogger().logMethod()

        CustomLogger().d("isSuccess: $isSuccess")
//        if (isSuccess) {
//            image =
//        }
    }

}