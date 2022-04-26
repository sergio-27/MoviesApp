package com.doonamis.themoviesapp.data.remote

import com.doonamis.themoviesapp.model.TvShow
import com.doonamis.themoviesapp.model.response.PopularTvShowsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieApiService @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesService {

    override suspend fun getPopularTvShows(page: Int): List<TvShow> {
        return withContext(Dispatchers.IO) {
            val response: Response<PopularTvShowsResponse> = moviesApi.getPopularTvShows(page = page)
            response.body()?.results ?: emptyList()
        }
    }
}