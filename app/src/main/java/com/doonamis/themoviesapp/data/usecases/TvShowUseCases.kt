package com.doonamis.themoviesapp.data.usecases

import com.doonamis.themoviesapp.data.repositories.TvShowRepository
import com.doonamis.themoviesapp.model.TvShow
import com.doonamis.themoviesapp.model.toDatabase
import javax.inject.Inject

class TvShowUseCases @Inject constructor(
    private val tvShowRepository: TvShowRepository
){

//  suspend operator fun invoke() = moviesRepository.getPopularTvShows(1)
  //  suspend fun getPopularTvShows(page: Int) = tvShowRepository.getPopularTvShowsFromApi(page)

    suspend fun getPopularTvShows(page: Int, language: String): List<TvShow> {
        val popularTvShows = tvShowRepository.getPopularTvShowsFromApi(page, language)

       return if (popularTvShows.isNotEmpty()) {
            //everything okey, insert to local db
            tvShowRepository.clearTvShows()
            tvShowRepository.insertPopularTvShows(popularTvShows.map { it.toDatabase() })
            popularTvShows
        } else {
            //return local data
            tvShowRepository.getPopularTvShowsFromRoom()
        }
    }
}