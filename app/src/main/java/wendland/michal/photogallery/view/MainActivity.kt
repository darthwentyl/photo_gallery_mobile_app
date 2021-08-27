package wendland.michal.photogallery.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.result.contract.ActivityResultContracts
import wendland.michal.photogallery.R
import wendland.michal.photogallery.R.string.app_name
import wendland.michal.photogallery.const.PutExtrasNames
import wendland.michal.photogallery.setting.SettingsActivity

class MainActivity : BaseActivity() {
    private var LOG_TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = getString(R.string.title_app)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                openSettingsActivity.launch(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> {
                Log.w(LOG_TAG, "unresolved id is clicked item.itemId: " + item.itemId)
                super.onOptionsItemSelected(item)
            }
        }
    }

    private val openSettingsActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK)  {
            if (result.data?.getBooleanExtra(PutExtrasNames.IS_LANG_CHANGE, false) == true) {
                recreate()
            }
            Toast.makeText(this, getString(R.string.change_lang_info), Toast.LENGTH_SHORT).show()
        }
    }
}

