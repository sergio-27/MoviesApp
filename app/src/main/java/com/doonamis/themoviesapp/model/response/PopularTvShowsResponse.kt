package com.doonamis.themoviesapp.model.response

import com.doonamis.themoviesapp.model.TvShow

data class PopularTvShowsResponse(
    var page: Int,
    val results: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)