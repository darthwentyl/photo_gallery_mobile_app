package wendland.michal.photogallery.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import wendland.michal.photogallery.helper.ApplicationLanguageHelper
import wendland.michal.photogallery.helper.CustomLogger

open class BaseActivity: AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        CustomLogger().logMethod()
        val langCode = PreferenceManager.getDefaultSharedPreferences(newBase)
            .getString("language", "sys").toString()

        super.attachBaseContext(newBase?.let {
            ApplicationLanguageHelper.updateLocale(it, langCode)
        })
    }
}