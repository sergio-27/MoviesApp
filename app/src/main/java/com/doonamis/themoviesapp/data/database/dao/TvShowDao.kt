package com.doonamis.themoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doonamis.themoviesapp.data.database.entities.TvShowDb

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvshows_table")
    suspend fun getPopularTvShows(): List<TvShowDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvshows: List<TvShowDb>)

    @Query("DELETE FROM tvshows_table")
    suspend fun deleteAllTvShows()
}