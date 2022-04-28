package com.doonamis.themoviesapp.di

import android.content.Context
import android.content.res.Resources
import androidx.annotation.NonNull
import com.doonamis.themoviesapp.data.remote.TvShowApiService
import com.doonamis.themoviesapp.data.remote.TvShowApi
import com.doonamis.themoviesapp.data.repositories.TvShowRepository
import com.doonamis.themoviesapp.navigation.TvShowAppNavigator
import com.doonamis.themoviesapp.utils.RequestInterceptor
import com.doonamis.themoviesapp.utils.URLs
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMyHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(OkHttpProfilerInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, @NonNull okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URLs.api_base_url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesResources(@ApplicationContext context: Context): Resources = context.resources

    @Singleton
    @Provides
    fun providesMoviesAppNavigator(): TvShowAppNavigator = TvShowAppNavigator.Impl()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): TvShowApi = retrofit.create(TvShowApi::class.java)

    @Singleton
    @Provides
    fun providesMovieApiService(tvShowApi: TvShowApi) = TvShowApiService(tvShowApi)

    @Singleton
    @Provides
    fun providesMovieRepository(moviesApiService: TvShowApiService) = TvShowRepository(moviesApiService)

}