package com.doonamis.themoviesapp.navigation

import android.content.Context
import javax.inject.Inject

abstract class TvShowAppNavigator {

    abstract fun navToMovieDetails(context: Context)

    class Impl @Inject constructor(): TvShowAppNavigator() {

        override fun navToMovieDetails(context: Context) {

        }

    }
}