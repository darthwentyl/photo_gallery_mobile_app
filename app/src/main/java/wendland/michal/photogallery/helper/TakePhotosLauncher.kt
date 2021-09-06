package wendland.michal.photogallery.helper

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import wendland.michal.photogallery.R


class TakePhotosLauncher(
    private val packageContext: AppCompatActivity,
    private val cls: Class<*>
) {
    fun launch() {
        CustomLogger().logMethod()
        checkPermissionsAndLaunch()
    }

    private fun checkPermissionsAndLaunch() {
        CustomLogger().logMethod()
        when {
            ContextCompat.checkSelfPermission(
                packageContext,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                CustomLogger().d("PackageManager.PERMISSION_GRANTED")
                requestCameraPermission.launch(Manifest.permission.CAMERA)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                packageContext,
                Manifest.permission.CAMERA
            ) -> {
                CustomLogger().d("PackageManager.CAMERA")
                requestCameraPermission.launch(Manifest.permission.CAMERA)
            }

            else -> {
                CustomLogger().d("else")
                requestCameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private val openTakePhotoActivity = packageContext.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        CustomLogger().logMethod()

        if (it.resultCode == Activity.RESULT_OK) {
            var msg = "Take photo activity is finished"
            Toast.makeText(packageContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private val requestCameraPermission = packageContext.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        CustomLogger().logMethod()
        if (isGranted) {
            openTakePhotoActivity.launch(Intent(packageContext, cls))
        } else {
            CustomLogger().d("else")
            var msg = packageContext.getString(R.string.explain_perm_take_photo)
            Toast.makeText(packageContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

}

