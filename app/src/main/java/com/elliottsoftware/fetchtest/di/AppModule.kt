package com.elliottsoftware.fetchtest.di

import com.elliottsoftware.fetchtest.data.remote.FetchApi
import com.elliottsoftware.fetchtest.data.repositories.RemoteRepositoryImpl
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    fun provideFetchApi():FetchApi{
        return Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchApi::class.java)
    }



    @Provides
    fun provideRemoteRepository(
        fetchApi: FetchApi
    ):RemoteRepository{
        return RemoteRepositoryImpl(
            fetchApi
        )
    }



}