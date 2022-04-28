package com.doonamis.themoviesapp.data.usecases

import com.doonamis.themoviesapp.data.repositories.MoviesRepository
import javax.inject.Inject

class MovieUseCases @Inject constructor(
    private val moviesRepository: MoviesRepository
){
//    suspend operator fun invoke() = moviesRepository.getPopularTvShows(1)

    suspend fun getPopularTvShows(page: Int) = moviesRepository.getPopularTvShows(page)
}