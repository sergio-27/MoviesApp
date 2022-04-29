package com.doonamis.themoviesapp.model

import android.os.Parcelable
import com.doonamis.themoviesapp.data.database.entities.TvShowDb
import com.doonamis.themoviesapp.utils.URLs
import kotlinx.parcelize.Parcelize

@Parcelize
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
    val original_name: String
) : Parcelable {
    fun getPosterUrl() = "${URLs.base_image_url}$poster_path"

}

fun TvShow.toDatabase() =
    TvShowDb(
        id = id,
        poster_path = poster_path,
        popularity = popularity,
        backdrop_path = backdrop_path,
        vote_average = vote_average,
        overview = overview,
        first_air_date = first_air_date,
        origin_country = origin_country,
        genre_ids = genre_ids,
        original_language = original_language,
        vote_count = vote_count,
        name = name,
        original_name = original_name
    )
