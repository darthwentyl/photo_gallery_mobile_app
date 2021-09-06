package wendland.michal.photogallery.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.preference.PreferenceManager
import wendland.michal.photogallery.R
import wendland.michal.photogallery.data.PutExtrasNames
import wendland.michal.photogallery.fragment.SettingsFragment
import wendland.michal.photogallery.helper.CustomLogger


class SettingsActivity : BaseActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var sharedPref: SharedPreferences
    private var isLangChange: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        CustomLogger().logMethod()

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
        CustomLogger().logMethod()

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
        CustomLogger().logMethod()

        super.onPause()
        setExtras()
        sharedPref.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onResume() {
        CustomLogger().logMethod()

        super.onResume()
        sharedPref.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        CustomLogger().logMethod()

        if (p1 == getString(R.string.language_pref)) {
            isLangChange = true
            recreate()
        }
    }

    override fun onBackPressed() {
        CustomLogger().logMethod()

        setExtras()
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        CustomLogger().logMethod()

        super.onSaveInstanceState(outState)
        outState.putBoolean(PutExtrasNames.IS_LANG_CHANGE, isLangChange)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        CustomLogger().logMethod()

        super.onRestoreInstanceState(savedInstanceState)
        isLangChange = savedInstanceState.getBoolean(PutExtrasNames.IS_LANG_CHANGE)
    }

    private fun setExtras() {
        CustomLogger().logMethod()

        CustomLogger().d("isLangChange: " + isLangChange)
        val result = Intent()
        result.putExtra(PutExtrasNames.IS_LANG_CHANGE, isLangChange)
        setResult(RESULT_OK, result)
    }
}