package wendland.michal.photogallery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import wendland.michal.photogallery.databinding.FragmentTakePhotoListBinding
import wendland.michal.photogallery.helper.CustomLogger

class TakePhotoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CustomLogger().logMethod()
        val binding: FragmentTakePhotoListBinding = FragmentTakePhotoListBinding.inflate(inflater)

        binding.buttonFragment1.setOnClickListener{
            CustomLogger().logMethod()
            findNavController().navigate(
                TakePhotoListFragmentDirections
                    .actionTakePhotoListFragmentToTakePhotoImageViewFragment())
        }

        return binding.root
    }

}