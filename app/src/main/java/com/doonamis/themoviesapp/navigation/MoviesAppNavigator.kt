package com.doonamis.themoviesapp.navigation

import android.content.Context
import javax.inject.Inject

abstract class MoviesAppNavigator {

    abstract fun navToMovieDetails(context: Context)

    class Impl @Inject constructor(): MoviesAppNavigator() {

        override fun navToMovieDetails(context: Context) {

        }

    }
}