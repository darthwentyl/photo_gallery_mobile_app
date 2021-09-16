package wendland.michal.photogallery.listener

import androidx.navigation.NavController
import wendland.michal.photogallery.fragment.TakePhotoListFragmentDirections
import wendland.michal.photogallery.helper.CustomLogger

class TakePhotoCardClickListener(private val navController: NavController)
    : ITakePhotoCardClickListener {
    override fun onCardClick(position: Int) {
        CustomLogger.logMethod()
        CustomLogger.d("position: $position")

        val action =
            TakePhotoListFragmentDirections.actionTakePhotoListFragmentToTakePhotoImageViewFragment()
        action.position = position
        navController.navigate(action)
    }
}