package wendland.michal.photogallery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import wendland.michal.photogallery.R
import wendland.michal.photogallery.adapter.TakePhotoRecyclerAdapter
import wendland.michal.photogallery.controller.CameraController
import wendland.michal.photogallery.database.DataBaseStub
import wendland.michal.photogallery.database.IDataBase
import wendland.michal.photogallery.viewmodel.TakePhotosViewModel
import wendland.michal.photogallery.databinding.FragmentTakePhotoListBinding
import wendland.michal.photogallery.helper.CustomLogger
import wendland.michal.photogallery.listener.NewPhotoListener
import wendland.michal.photogallery.listener.TakePhotoCardClickListener

class TakePhotoListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cameraController: CameraController
    private lateinit var adapter: TakePhotoRecyclerAdapter
    private lateinit var takePhotosViewModel: TakePhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        CustomLogger.logMethod()

        takePhotosViewModel = ViewModelProvider(requireActivity()).get(TakePhotosViewModel::class.java)
        adapter = TakePhotoRecyclerAdapter(takePhotosViewModel, TakePhotoCardClickListener(findNavController()))
        cameraController = CameraController(this, NewPhotoListener(takePhotosViewModel, adapter))

        val binding: FragmentTakePhotoListBinding = FragmentTakePhotoListBinding.inflate(inflater)
        binding.apply {
            recyclerView = root.findViewById(R.id.take_photo_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(root.context)
            recyclerView.adapter = adapter
            takePhotoNewButton.setOnClickListener {
                cameraController.launch()
            }
            takePhotoCancelButton.setOnClickListener {
                takePhotosViewModel.clear()
                adapter.notifyDataSetChanged()
            }
            takePhotoSyncButton.setOnClickListener{
                val dataBase: IDataBase = DataBaseStub(it.context)
                dataBase.sync()
            }
        }
        return binding.root
    }
}