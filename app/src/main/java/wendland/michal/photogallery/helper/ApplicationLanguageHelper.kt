package wendland.michal.photogallery.helper

import android.content.Context
import android.content.ContextWrapper
import java.util.*

class ApplicationLanguageHelper(base: Context) : ContextWrapper(base) {
    companion object {
        fun updateLocale(c: Context, langCode: String) : ContextWrapper {
            CustomLogger.logMethod()
            var context = c
            val resources = context.resources
            val config = resources.configuration
            val locale = Locale(langCode)
            Locale.setDefault(locale)
            config.setLocale(locale)
            context = context.createConfigurationContext(config)
            return ApplicationLanguageHelper(context)
        }
    }
}