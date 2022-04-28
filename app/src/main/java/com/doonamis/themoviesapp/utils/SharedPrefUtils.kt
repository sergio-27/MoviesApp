package com.doonamis.themoviesapp.utils

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPrefUtils(context: Context?) {


    companion object {
        private val THEME_MODE = "THEME_STATUS"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context!!)

    var themeMode = prefs.getInt(THEME_MODE, 0)
        set(value) = prefs.edit().putInt(THEME_MODE, value).apply()
}