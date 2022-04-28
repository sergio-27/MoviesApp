package com.doonamis.themoviesapp.data.remote

import com.doonamis.themoviesapp.model.TvShow

interface TvShowService {

    suspend fun getPopularTvShows(page: Int): List<TvShow>
}