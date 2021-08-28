package wendland.michal.photogallery.view

import android.content.Intent
import android.os.Bundle
import wendland.michal.photogallery.R
import wendland.michal.photogallery.helper.CustomLogger

class TakePhotoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        CustomLogger().logMethod()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)

        this.title = getString(R.string.title_app)
    }

    override fun onBackPressed() {
        CustomLogger().logMethod()

        val result = Intent()
        setResult(RESULT_OK, result)
        super.onBackPressed()
    }
}