package wendland.michal.photogallery.helper

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import wendland.michal.photogallery.data.PutExtrasNames.RETURN_MESSAGE


class TakePhotosLauncher(
    private val packageContext: AppCompatActivity,
    private val cls: Class<*>
) {
    private var permissionsArray: Array<String> = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

    fun launch() {
        CustomLogger().logMethod()
        checkPermissionsAndLaunch()
    }

    private fun checkPermissionsAndLaunch() {
        CustomLogger().logMethod()
        requestPermissions.launch(permissionsArray)
    }

    private val openTakePhotoActivity = packageContext.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        CustomLogger().logMethod()

        if (it.resultCode == Activity.RESULT_OK) {
            var msg = "Take photo activity is finished"
            Toast.makeText(packageContext, msg, Toast.LENGTH_SHORT).show()
        } else {
            CustomLogger().w("it.resultCode: " + it.resultCode)
            val msg: String? = it.data?.getStringExtra(RETURN_MESSAGE)
            if (msg != null) {
                Toast.makeText(packageContext, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val requestPermissions = packageContext.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
        CustomLogger().logMethod()

        var granted = true
        map.forEach { entry ->
            CustomLogger().d("key: " + entry.key + " value: " + entry.value)
            if (!entry.value) {
                granted = false
                var msg = PermissionExplainMsg(packageContext).getMsg(entry.key)
                Toast.makeText(packageContext, msg, Toast.LENGTH_SHORT).show()
            }
        }
        if (granted) {
            openTakePhotoActivity.launch(Intent(packageContext, cls))
        }
    }

}

