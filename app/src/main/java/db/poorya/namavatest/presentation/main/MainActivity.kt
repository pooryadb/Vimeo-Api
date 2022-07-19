package db.poorya.namavatest.presentation.main

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import db.poorya.namavatest.databinding.ActivityMainBinding
import db.poorya.namavatest.utils.language.LocaleUtils

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(requireNotNull(binding).root)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleUtils.setLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
    }

    override fun onStart() {
        binding = null
        super.onStart()
    }

}