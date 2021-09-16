package wendland.michal.photogallery.data

import android.widget.ImageView
import android.widget.TextView

data class PhotoCardData(
    var imageView: ImageView,
    var date: TextView,
    var gpsPosition: TextView
)
