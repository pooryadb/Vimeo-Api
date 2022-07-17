package db.poorya.namavatest.utils.language

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*

class LocaleUtils {
    companion object {

        //force language
        private const val language = "en"

        @JvmStatic
        fun setLocale(c: Context): Context {
            return updateResources(
                c
            )
        }

        fun setNewLocale(c: Context, language: String): Context {
            return updateResources(
                c,
                language
            )
        }

        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= 24)
                config.locales.get(0)
            else config.locale
        }

        @JvmStatic
        private fun updateResources(
            context: Context, language: String = Companion.language
        ): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val res = context.resources
            val config = Configuration(res.configuration)
            config.setLocale(locale)

            config.locale = locale
            context.createConfigurationContext(config)
            res.updateConfiguration(config, res.displayMetrics)

            return context.createConfigurationContext(config)
        }
    }
}