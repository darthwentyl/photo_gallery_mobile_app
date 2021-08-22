package wendland.michal.photogallery.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import wendland.michal.photogallery.R

class SettingsActivity : AppCompatActivity() {
    private var TAG = "SettingsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate()")
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
        private var TAG = "SettingsFragment"

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            Log.d(TAG, "onCreatePreferences()")
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            bindPreferenceSummaryToValue(findPreference(getString(R.string.language_pref)))
        }

        private fun bindPreferenceSummaryToValue(preference: Preference?) {
            Log.d(TAG, "bindPreferenceSummaryToValue()")
            preference?.onPreferenceChangeListener = this
            onPreferenceChange(preference,
                PreferenceManager
                    .getDefaultSharedPreferences(preference?.context)
                    .getString(preference?.key, ""))
        }

        override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
            val stringValue = newValue.toString()
            if (preference is ListPreference) {
                val prefIdx = preference.findIndexOfValue(stringValue)
                if (prefIdx >= 0) {
                    val sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext().applicationContext)
                    sharedPref.edit().putString("language", stringValue).apply()
                }
            }
            return true
        }
    }
}