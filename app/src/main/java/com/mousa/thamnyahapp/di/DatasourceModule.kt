package com.mousa.thamnyahapp.di

import com.mousa.thamnyahapp.data.remote.api.HomeApiService
import com.mousa.thamnyahapp.data.remote.datasource.HomeSectionsDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    @Singleton
    fun provideSectionsDatasource(apiService:HomeApiService): HomeSectionsDatasource =
        HomeSectionsDatasource(apiService)
}