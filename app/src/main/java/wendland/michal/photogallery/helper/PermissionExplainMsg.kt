package wendland.michal.photogallery.helper

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import wendland.michal.photogallery.R

class PermissionExplainMsg(private val packageContext: AppCompatActivity)
{
    fun getMsg(permissionKey: String) : String {
        return when (permissionKey) {
            Manifest.permission.CAMERA -> packageContext.getString(R.string.explain_perm_take_photo)
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> packageContext.getString(R.string.explain_perm_write_ext_storage)
            else -> ""
        }
    }
}