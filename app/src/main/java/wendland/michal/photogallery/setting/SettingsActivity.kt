package wendland.michal.photogallery.setting

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.preference.PreferenceManager
import wendland.michal.photogallery.R
import wendland.michal.photogallery.const.PutExtrasNames
import wendland.michal.photogallery.fragment.SettingsFragment
import wendland.michal.photogallery.view.BaseActivity



class SettingsActivity : BaseActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private var LOG_TAG = "SettingsActivity"
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        setContentView(R.layout.settings_activity)
        this.title = getString(R.string.setting_str)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                setExtras()
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onPause() {
        Log.i(LOG_TAG, "onPause()")
        super.onPause()
        setExtras()
        sharedPref.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onResume() {
        Log.i(LOG_TAG, "onResume()")
        super.onResume()
        sharedPref.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if (p1 == getString(R.string.language_pref)) {
            recreate()
        }
    }

    override fun onBackPressed() {
        setExtras()
        super.onBackPressed()
    }

    private fun setExtras() {
        val result = Intent()
        result.putExtra(PutExtrasNames.IS_LANG_CHANGE, true)
        setResult(RESULT_OK, result)
    }

}