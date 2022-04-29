package com.doonamis.themoviesapp.data.repositories

import com.doonamis.themoviesapp.data.database.dao.TvShowDao
import com.doonamis.themoviesapp.data.database.entities.TvShowDb
import com.doonamis.themoviesapp.data.database.entities.toModel
import com.doonamis.themoviesapp.data.remote.TvShowService
import com.doonamis.themoviesapp.model.TvShow
import javax.inject.Inject

class TvShowRepository @Inject constructor(
    private val tvShowApi: TvShowService,
    private val tvShowDao: TvShowDao
){

    suspend fun getPopularTvShowsFromApi(tvShowPage: Int, language: String): List<TvShow> {
        return tvShowApi.getPopularTvShows(tvShowPage, language)
    }

    suspend fun getPopularTvShowsFromRoom(): List<TvShow> {
        val response: List<TvShowDb> = tvShowDao.getPopularTvShows()
        return response.map { it.toModel() }
    }

    suspend fun insertPopularTvShows(tvshows: List<TvShowDb>) {
        tvShowDao.insertAll(tvshows)
    }

    suspend fun clearTvShows() {
        tvShowDao.deleteAllTvShows()
    }
}