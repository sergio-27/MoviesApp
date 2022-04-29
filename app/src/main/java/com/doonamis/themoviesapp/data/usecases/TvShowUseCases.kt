package com.doonamis.themoviesapp.data.usecases

import android.content.Context
import com.doonamis.themoviesapp.data.repositories.TvShowRepository
import com.doonamis.themoviesapp.model.TvShow
import com.doonamis.themoviesapp.model.toDatabase
import com.doonamis.themoviesapp.utils.Utils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TvShowUseCases @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    @ApplicationContext val context: Context
){

//  suspend operator fun invoke() = moviesRepository.getPopularTvShows(1)

    suspend fun getPopularTvShows(page: Int, language: String): List<TvShow> {

        return if (Utils.isNetworkAvailable(context)) {
            val popularTvShows = tvShowRepository.getPopularTvShowsFromApi(page, language)
            //everything okey, insert to local db
            tvShowRepository.insertPopularTvShows(popularTvShows.map { it.toDatabase() })
            popularTvShows
        } else {
            //return local data
            tvShowRepository.getPopularTvShowsFromRoom()
        }
    }
}