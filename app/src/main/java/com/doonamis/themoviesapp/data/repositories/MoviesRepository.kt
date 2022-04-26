package com.doonamis.themoviesapp.data.repositories

import com.doonamis.themoviesapp.data.remote.MovieApiService
import com.doonamis.themoviesapp.data.remote.MoviesService
import com.doonamis.themoviesapp.model.TvShow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesService
){

    suspend fun getPopularTvShows(): List<TvShow> {
        val response: List<TvShow> = moviesApi.getPopularTvShows()
        //todo save data locally
        return response
    }
}