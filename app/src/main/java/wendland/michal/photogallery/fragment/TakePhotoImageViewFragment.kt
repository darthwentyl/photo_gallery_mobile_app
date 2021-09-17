package wendland.michal.photogallery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import wendland.michal.photogallery.databinding.FragmentTakePhotoImageViewBinding
import wendland.michal.photogallery.helper.CustomLogger

class TakePhotoImageViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CustomLogger().logMethod()
        val binding: FragmentTakePhotoImageViewBinding =
            FragmentTakePhotoImageViewBinding.inflate(inflater)

        binding.buttonFragment2.setOnClickListener{
            CustomLogger().logMethod()
            findNavController().navigate(
                TakePhotoImageViewFragmentDirections
                    .actionTakePhotoImageViewFragmentToTakePhotoListFragment())
        }

        return binding.root
    }

}