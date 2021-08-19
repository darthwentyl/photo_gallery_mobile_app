package wendland.michal.photogallery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import wendland.michal.photogallery.R
import wendland.michal.photogallery.setting.SettingsActivity

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                Log.d(TAG, "R.id.settings is clicked")
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> {
                Log.d(TAG, "unresolved id is clicked item.itemId: " + item.itemId)
                super.onOptionsItemSelected(item)
            }
        }
    }
}