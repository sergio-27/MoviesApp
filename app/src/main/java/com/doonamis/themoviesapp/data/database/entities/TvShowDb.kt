package com.doonamis.themoviesapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doonamis.themoviesapp.model.TvShow

@Entity(tableName = "tvshows_table")
data class TvShowDb(
    @PrimaryKey
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
)

fun TvShowDb.toModel() = TvShow(id,  poster_path, popularity, backdrop_path, vote_average, overview, first_air_date, origin_country, genre_ids, original_language, vote_count, name, original_name)