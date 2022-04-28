package com.doonamis.themoviesapp.data.usecases

import com.doonamis.themoviesapp.data.repositories.TvShowRepository
import javax.inject.Inject

class TvShowUseCases @Inject constructor(
    private val tvShowRepository: TvShowRepository
){

//  suspend operator fun invoke() = moviesRepository.getPopularTvShows(1)
    suspend fun getPopularTvShows(page: Int) = tvShowRepository.getPopularTvShows(page)
}