package wendland.michal.photogallery.fragment

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import wendland.michal.photogallery.R
import wendland.michal.photogallery.helper.CustomLogger

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        CustomLogger().logMethod()
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        bindPreferenceSummaryToValue(findPreference(getString(R.string.language_pref)))
    }

    private fun bindPreferenceSummaryToValue(preference: Preference?) {
        CustomLogger().logMethod()
        preference?.onPreferenceChangeListener = this
        onPreferenceChange(preference,
            PreferenceManager
                .getDefaultSharedPreferences(preference?.context)
                .getString(preference?.key, ""))
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        CustomLogger().logMethod()
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