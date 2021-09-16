package wendland.michal.photogallery.listener

import android.net.Uri

interface INewPhotoListener {
    fun onTakeNewPhoto(imageUri: Uri, absolutePath: String)
}