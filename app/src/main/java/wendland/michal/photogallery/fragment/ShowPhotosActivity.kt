package wendland.michal.photogallery.fragment

import android.content.Intent
import android.os.Bundle
import wendland.michal.photogallery.R
import wendland.michal.photogallery.helper.CustomLogger
import wendland.michal.photogallery.activity.BaseActivity

class ShowPhotosActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        CustomLogger.logMethod()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_photos)

        this.title = getString(R.string.title_app)
    }

    override fun onBackPressed() {
        CustomLogger.logMethod()

        val result = Intent()
        setResult(RESULT_OK, result)
        super.onBackPressed()
    }
}