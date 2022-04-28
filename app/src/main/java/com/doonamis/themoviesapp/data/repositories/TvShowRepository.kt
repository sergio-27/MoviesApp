package com.doonamis.themoviesapp.data.repositories

import com.doonamis.themoviesapp.data.remote.TvShowService
import com.doonamis.themoviesapp.model.TvShow
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val tvShowApi: TvShowService
){

    suspend fun getPopularTvShows(tvShowPage: Int): List<TvShow> {
        val response: List<TvShow> = tvShowApi.getPopularTvShows(tvShowPage)
        //todo save data locally
        return response
    }
}