package com.doonamis.themoviesapp.utils

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPrefUtils(context: Context?) {


    companion object {
        private const val THEME_MODE = "THEME_STATUS"
        private const val LANG_SELECTED = "LANG_SELECTED"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context!!)

    var themeMode = prefs.getInt(THEME_MODE, 0)
        set(value) = prefs.edit().putInt(THEME_MODE, value).apply()

    var langOption = prefs.getInt(LANG_SELECTED, 0)
        set(value) = prefs.edit().putInt(LANG_SELECTED, value).apply()
}