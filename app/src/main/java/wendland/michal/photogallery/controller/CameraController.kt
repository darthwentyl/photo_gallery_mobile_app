package wendland.michal.photogallery.controller

import android.content.Context
import android.content.pm.PackageManager
import wendland.michal.photogallery.helper.CustomLogger

class CameraController(private val context: Context) {

    fun launch(): Boolean {
        CustomLogger().logMethod()

        return if (checkCameraHardware()) {
            CustomLogger().d("true")
            true
        } else {
            CustomLogger().d("false")
            false
        }
    }

    private fun checkCameraHardware(): Boolean {
        CustomLogger().logMethod()

        return if (context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            CustomLogger().d("true")
            true
        } else {
            CustomLogger().d("false")
            false
        }
    }
}