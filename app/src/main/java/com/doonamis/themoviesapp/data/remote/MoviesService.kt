package com.doonamis.themoviesapp.data.remote

import com.doonamis.themoviesapp.model.TvShow
import io.reactivex.Observable

interface MoviesService {

    suspend fun getPopularTvShows(): List<TvShow>
}