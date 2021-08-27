package wendland.michal.photogallery.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import wendland.michal.photogallery.helper.ApplicationLanguageHelper

open class BaseActivity: AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        val langCode = PreferenceManager.getDefaultSharedPreferences(newBase)
            .getString("language", "sys").toString()

        super.attachBaseContext(newBase?.let {
            ApplicationLanguageHelper.updateLocale(it, langCode)
        })
    }
}