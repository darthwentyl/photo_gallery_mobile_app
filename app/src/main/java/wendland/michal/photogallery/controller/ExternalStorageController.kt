package wendland.michal.photogallery.controller

import android.content.Context
import android.os.Environment
import wendland.michal.photogallery.helper.CustomLogger
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object ExternalStorageController {
    fun createImageFile(context: Context): File? {
        CustomLogger.logMethod()

        val timeStamp: String = SimpleDateFormat("dd-MM-yyyy_hh_mm_ss", Locale.US).format(Date())
//        TODO: maybe it should be adjustable
        val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES + "/my_images")!!
        if (!storageDir.exists()) {
            storageDir.mkdirs()
        }
        CustomLogger.d("timeStamp: $timeStamp, storageDir: $storageDir")
        return File.createTempFile("$timeStamp", ".png", storageDir)
    }

    fun deleteImage(imagePath: String) {
        val file = File(imagePath)
        if(file.delete()) {
            CustomLogger.d("${imagePath} is deleted")
        } else {
            CustomLogger.d("${imagePath} is not deleted")
        }
    }
}

