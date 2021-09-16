package wendland.michal.photogallery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import wendland.michal.photogallery.controller.ExternalStorageController
import wendland.michal.photogallery.data.PhotoData
import wendland.michal.photogallery.helper.CustomLogger
import java.io.File

class TakePhotosViewModel : ViewModel() {
    private var photoDataList: MutableLiveData<MutableList<PhotoData>> = MutableLiveData()

    init {
        photoDataList.value = mutableListOf()
    }

    fun add(element: PhotoData) {
        photoDataList.value!!.add(element)
    }

    fun get(index: Int): PhotoData {
        if (index > photoDataList.value!!.size) {
            throw IndexOutOfBoundsException()
        }
        return photoDataList.value!![index]
    }

    fun size(): Int {
        return photoDataList.value!!.size
    }

    fun clear() {
        deleteTmpImages()
        photoDataList.value?.clear()
    }

    override fun onCleared() {
        CustomLogger.logMethod()
        deleteTmpImages()
    }

    private fun deleteTmpImages() {
        photoDataList.value?.forEach {
            ExternalStorageController.deleteImage(it.absolutePath)
        }
    }
}