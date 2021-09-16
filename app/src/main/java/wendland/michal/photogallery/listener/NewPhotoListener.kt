package wendland.michal.photogallery.listener

import android.net.Uri
import androidx.fragment.app.Fragment
import wendland.michal.photogallery.adapter.TakePhotoRecyclerAdapter
import wendland.michal.photogallery.data.PhotoData
import wendland.michal.photogallery.viewmodel.TakePhotosViewModel
import wendland.michal.photogallery.helper.CustomLogger
import java.text.SimpleDateFormat
import java.util.*

class NewPhotoListener(
    private val takePhotosViewModel: TakePhotosViewModel,
    private val adapter: TakePhotoRecyclerAdapter
) : INewPhotoListener {
    override fun onTakeNewPhoto(imageUri: Uri, absolutePath: String) {
        CustomLogger.logMethod()

        takePhotosViewModel.add(
            PhotoData(imageUri,
                absolutePath,
                SimpleDateFormat("dd.MM.yyyy", Locale.US).format(Date()),
                "0:0"))

        adapter.notifyItemInserted(takePhotosViewModel.size())
    }
}