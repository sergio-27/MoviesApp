package com.doonamis.themoviesapp.data.remote

import com.doonamis.themoviesapp.model.TvShow
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieApiService @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesService {

    override suspend fun getPopularTvShows(): List<TvShow> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<TvShow>> = moviesApi.getPopularTvShows()
            response.body() ?: emptyList()
        }
    }
}