package com.doonamis.themoviesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.doonamis.themoviesapp.data.database.dao.TvShowDao
import com.doonamis.themoviesapp.data.database.entities.TvShowDb
import com.doonamis.themoviesapp.utils.Converters

@Database(entities = [TvShowDb::class], version = 2)
@TypeConverters(Converters::class)
abstract class PopularTvShowDatabase : RoomDatabase() {


    abstract fun getTvShowDao(): TvShowDao
}