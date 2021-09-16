package wendland.michal.photogallery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import wendland.michal.photogallery.viewmodel.TakePhotosViewModel
import wendland.michal.photogallery.databinding.FragmentTakePhotoImageViewBinding
import wendland.michal.photogallery.helper.CustomLogger

class TakePhotoImageViewFragment : Fragment() {

    private lateinit var takePhotosViewModel: TakePhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        CustomLogger.logMethod()

        val position = TakePhotoImageViewFragmentArgs.fromBundle(requireArguments()).position
        takePhotosViewModel = ViewModelProvider(requireActivity()).get(TakePhotosViewModel::class.java)

        val binding = FragmentTakePhotoImageViewBinding.inflate(inflater)
        binding.apply {
            takePhotoBigImageView.setImageURI(takePhotosViewModel.get(position).imageUri)
            backToTakePhotoImageList.setOnClickListener {
                CustomLogger.logMethod()
                findNavController().navigate(
                    TakePhotoImageViewFragmentDirections
                        .actionTakePhotoImageViewFragmentToTakePhotoListFragment())
            }
        }
        return binding.root
    }
}