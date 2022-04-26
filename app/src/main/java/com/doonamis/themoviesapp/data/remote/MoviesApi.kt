package com.doonamis.themoviesapp.data.remote


import com.doonamis.themoviesapp.model.response.PopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("page") page: Int): Response<PopularTvShowsResponse>
}