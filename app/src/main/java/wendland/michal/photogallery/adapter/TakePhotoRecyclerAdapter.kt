package wendland.michal.photogallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import wendland.michal.photogallery.R
import wendland.michal.photogallery.data.PhotoCardData
import wendland.michal.photogallery.viewmodel.TakePhotosViewModel
import wendland.michal.photogallery.helper.CustomLogger
import wendland.michal.photogallery.listener.ITakePhotoCardClickListener

class TakePhotoRecyclerAdapter(
    private val takePhotosViewModel: TakePhotosViewModel,
    private val cardClickListener: ITakePhotoCardClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        CustomLogger.logMethod()
        val view = LayoutInflater.from(parent.context).inflate(R.layout.take_photo_card_layout, parent, false)
        return ViewHolder(view, cardClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        CustomLogger.logMethod()

        CustomLogger.d("position: $position")
        var viewHolder: ViewHolder = holder as ViewHolder
        val photoData = takePhotosViewModel.get(position)
        viewHolder.photoCardData?.imageView?.setImageURI(photoData.imageUri)
        viewHolder.photoCardData?.date?.text = photoData.date
        viewHolder.photoCardData?.gpsPosition?.text = photoData.gpsPosition
    }

    override fun getItemCount(): Int {
        CustomLogger.logMethod()
        CustomLogger.d("takePhotosViewModel.size(): ${takePhotosViewModel.size()}")
        return takePhotosViewModel.size()
    }

    companion object {
        class ViewHolder : RecyclerView.ViewHolder {
            var photoCardData: PhotoCardData? = null

            constructor(itemView: View, cardClickListener: ITakePhotoCardClickListener) : super(itemView) {
                val imageView = itemView.findViewById<ImageView>(R.id.take_photo_image_view)
                val date = itemView.findViewById<TextView>(R.id.take_photo_date_item)
                val gpsPosition = itemView.findViewById<TextView>(R.id.take_photo_gps_position_item)
                photoCardData = PhotoCardData(imageView, date, gpsPosition)

                itemView.setOnClickListener {
                    cardClickListener.onCardClick(adapterPosition)
                }
            }
        }
    }
}