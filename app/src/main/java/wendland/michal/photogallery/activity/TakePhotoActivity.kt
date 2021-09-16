package wendland.michal.photogallery.activity

import android.content.Intent
import android.os.Bundle
import wendland.michal.photogallery.R
import wendland.michal.photogallery.data.PutExtrasNames.RETURN_MESSAGE
import wendland.michal.photogallery.helper.CustomLogger

class TakePhotoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        CustomLogger.logMethod()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)

        this.title = getString(R.string.title_app)
    }

    override fun onBackPressed() {
        CustomLogger.logMethod()

        closeActivity(true)
        super.onBackPressed()
    }

    private fun closeActivity(status: Boolean, msg: String = "") {
        CustomLogger.logMethod()

        val result = Intent()

        result.putExtra(RETURN_MESSAGE, msg)

        val resultCode = if (status) RESULT_OK else RESULT_CANCELED
        setResult(resultCode, result)
    }
}