package com.doonamis.themoviesapp.ui.tvshow_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doonamis.themoviesapp.data.usecases.TvShowUseCases
import com.doonamis.themoviesapp.model.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val tvShowUseCases: TvShowUseCases
) : ViewModel() {

    private var page = 0

    val popularTvShowsLiveData = MutableLiveData<List<TvShow>>()

    fun setPageToZero() {
        page = 0
    }

    fun getPopularTvShows() {

        viewModelScope.launch {
            page += 1
            //fetch paginated data from TMDB server
            val result = tvShowUseCases.getPopularTvShows(page)

            if (!result.isNullOrEmpty()){
                popularTvShowsLiveData.postValue(result)
            }
        }
    }


}