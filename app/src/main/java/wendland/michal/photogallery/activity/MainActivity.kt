package wendland.michal.photogallery.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import wendland.michal.photogallery.R
import wendland.michal.photogallery.data.PutExtrasNames
import wendland.michal.photogallery.helper.CustomLogger
import wendland.michal.photogallery.helper.TakePhotosLauncher

class MainActivity : BaseActivity() {

    private val takePhotosLauncher: TakePhotosLauncher =
        TakePhotosLauncher(this, TakePhotoActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        CustomLogger.logMethod()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.title = getString(R.string.title_app)

        val takePhotoBtn = findViewById<Button>(R.id.take_photo_btn)
        takePhotoBtn.setOnClickListener {
            takePhotosLauncher.launch()
        }

        val showPhotosActivity = findViewById<Button>(R.id.show_photos_btn)
        showPhotosActivity.setOnClickListener {
            openShowPhotosActivity.launch(Intent(this, ShowPhotosActivity::class.java))
        }

        val sendPhotosActivity = findViewById<Button>(R.id.send_photos_btn)
        sendPhotosActivity.setOnClickListener {
            openSendPhotosActivity.launch(Intent(this, ShowPhotosActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        CustomLogger.logMethod()

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        CustomLogger.logMethod()

        return when (item.itemId) {
            R.id.settings -> {
                openSettingsActivity.launch(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> {
                CustomLogger.w("unresolved id is clicked item.itemId: " + item.itemId)
                super.onOptionsItemSelected(item)
            }
        }
    }

    private val openSettingsActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        CustomLogger.logMethod()

        if (result.resultCode == Activity.RESULT_OK)  {
            CustomLogger.d("result.data?.getBooleanExtra(PutExtrasNames.IS_LANG_CHANGE, false): " + result.data?.getBooleanExtra(PutExtrasNames.IS_LANG_CHANGE, false))
            if (result.data?.getBooleanExtra(PutExtrasNames.IS_LANG_CHANGE, false) == true) {
                Toast.makeText(this, getString(R.string.change_lang_info), Toast.LENGTH_SHORT).show()
                recreate()
            }
        }
    }

    private val openShowPhotosActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        CustomLogger.logMethod()

        if (it.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Show photos activity is finished", Toast.LENGTH_SHORT).show()
        }
    }

    private val openSendPhotosActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        CustomLogger.logMethod()

        if (it.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Send photos activity is finished", Toast.LENGTH_SHORT).show()
        }
    }
}

