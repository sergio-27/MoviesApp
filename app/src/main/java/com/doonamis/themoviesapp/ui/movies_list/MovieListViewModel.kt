package com.doonamis.themoviesapp.ui.movies_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doonamis.themoviesapp.data.usecases.MovieUseCases
import com.doonamis.themoviesapp.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    val popularTvShowsLiveData = MutableLiveData<List<TvShow>>()

//    fun getPopularTvShows() : List<TvShow> {
//        return movieUseCases()
//    }

/*         viewModelScope.launch {
             val result = movieUseCases.getPopularTvShows(1)

             if (!result.isNullOrEmpty()){
                 popularTvShowsLiveData.postValue(result)
             }
         }
    }*/


    fun getPopularTvShows(page: Int) {
        viewModelScope.launch {
            val result = movieUseCases.getPopularTvShows(page)

            if (!result.isNullOrEmpty()){
                popularTvShowsLiveData.postValue(result)
            }
        }
    }


}