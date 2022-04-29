package com.doonamis.themoviesapp.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.doonamis.themoviesapp.R
import com.doonamis.themoviesapp.databinding.ActivityMainBinding
import com.doonamis.themoviesapp.utils.SharedPrefUtils
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var currentLang: String
    private lateinit var sharedPrefUtils: SharedPrefUtils
    private lateinit var locale: Locale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefUtils = SharedPrefUtils(this)

        checkCurrentTheme()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_change_theme -> openChooseThemeDialog()
            R.id.action_change_lang -> openChooseLang()
        }

        return true
    }

    private fun openChooseLang() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_lang))
        val options = arrayOf(
            getString(R.string.lang_option_es),
            getString(R.string.lang_option_en)
        )
        val checkedOptions = SharedPrefUtils(this).langOption

        builder.setSingleChoiceItems(options, checkedOptions) { dialog, which ->

            when (which) {
                0 -> {
                    //change to spanish
                    setLocale(getString(R.string.locale_language_option_es))
                    SharedPrefUtils(this).langOption = 0
                    dialog.dismiss()
                }
                1 -> {
                    //change to english
                    setLocale(getString(R.string.locale_language_option_en))
                    SharedPrefUtils(this).langOption = 1
                    dialog.dismiss()
                }
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun setLocale(language: String) {
        locale = Locale(language)
        var res = resources
        var dm = res.displayMetrics
        var conf = res.configuration
        conf.locale = locale
        res.updateConfiguration(conf, dm)

        var mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
    }


    private fun openChooseThemeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_theme_title))
        val options = arrayOf(
            getString(R.string.light_theme_text),
            getString(R.string.dark_theme_text),
            getString(R.string.system_default_theme_text)
        )
        val checkedOptions = sharedPrefUtils.themeMode

        builder.setSingleChoiceItems(options, checkedOptions) { dialog, which ->

            when (which) {
                0 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    sharedPrefUtils.themeMode = 0
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    sharedPrefUtils.themeMode = 1
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    sharedPrefUtils.themeMode = 2
                    delegate.applyDayNight()
                    dialog.dismiss()
                }
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun checkCurrentTheme() {
        when (sharedPrefUtils.themeMode) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
        }
    }

}