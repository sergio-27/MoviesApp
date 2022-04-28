package com.doonamis.themoviesapp.data.remote

import com.doonamis.themoviesapp.model.TvShow
import com.doonamis.themoviesapp.model.response.PopularTvShowsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class TvShowApiService @Inject constructor(
    private val tvShowApi: TvShowApi
) : TvShowService {

    override suspend fun getPopularTvShows(page: Int): List<TvShow> {
        return withContext(Dispatchers.IO) {
            val response: Response<PopularTvShowsResponse> = tvShowApi.getPopularTvShows(page = page)
            response.body()?.results ?: emptyList()
        }
    }
}