package wendland.michal.photogallery.view

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.preference.PreferenceManager
import wendland.michal.photogallery.R
import wendland.michal.photogallery.setting.SettingsActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        Log.i(TAG, "onResume()")
//        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
//        val langCode = sharedPref.getString("language", "sys").toString()
//        Log.i(TAG, "langCode: " + langCode)
//        val config = Configuration(resources.configuration)
//        val locale = Locale(langCode)
//        config.setLocale(locale)
//        this.createConfigurationContext(config)
//        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                Log.i(TAG, "R.id.settings is clicked")
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> {
                Log.i(TAG, "unresolved id is clicked item.itemId: " + item.itemId)
                super.onOptionsItemSelected(item)
            }
        }
    }
}