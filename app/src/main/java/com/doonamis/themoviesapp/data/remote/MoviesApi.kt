package com.doonamis.themoviesapp.data.remote

import com.doonamis.themoviesapp.model.TvShow
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("tv/popular")
    suspend fun getPopularTvShows(): Response<List<TvShow>>
}