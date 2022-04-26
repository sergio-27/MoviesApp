package com.doonamis.themoviesapp.model

import com.doonamis.themoviesapp.utils.URLs

//todo mapear
data class TvShow(
    val id: Int,
    val poster_path: String?,
    val popularity: Float,
    val backdrop_path: String?,
    val vote_average: Float,
    val overview: String,
    val first_air_date: String,
    val origin_country: List<String>,
    val genre_ids: List<Int>,
    val original_language: String,
    val vote_count: Int,
    val name: String,
    val original_name: String,
    var page: Int
){
    fun getPosterUrl() = "${URLs.base_image_url}$poster_path"
}