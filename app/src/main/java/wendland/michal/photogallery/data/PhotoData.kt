package wendland.michal.photogallery.data

import android.net.Uri

data class PhotoData(var imageUri: Uri,
                     var absolutePath: String,
                     var date: String,
                     var gpsPosition: String
)
