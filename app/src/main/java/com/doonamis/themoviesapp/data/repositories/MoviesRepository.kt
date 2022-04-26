package com.doonamis.themoviesapp.data.repositories

import com.doonamis.themoviesapp.data.remote.MoviesService
import com.doonamis.themoviesapp.model.TvShow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesService
){

    var tvShowPage: Int = 1

    suspend fun getPopularTvShows(): List<TvShow> {
        tvShowPage+=1
        val response: List<TvShow> = moviesApi.getPopularTvShows(tvShowPage)
        //todo save data locally
        return response
    }
}