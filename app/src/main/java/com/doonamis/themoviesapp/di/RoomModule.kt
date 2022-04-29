package com.doonamis.themoviesapp.di

import android.content.Context
import androidx.room.Room
import com.doonamis.themoviesapp.data.database.PopularTvShowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POPULAR_TVSHOW_DATABASE_NAME = "popular_tvshow_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PopularTvShowDatabase::class.java, POPULAR_TVSHOW_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideTvShowDao(db: PopularTvShowDatabase) = db.getTvShowDao()
}