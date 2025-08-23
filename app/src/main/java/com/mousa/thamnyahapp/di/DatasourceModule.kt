package com.mousa.thamnyahapp.di

import com.mousa.thamnyahapp.data.remote.api.HomeApiService
import com.mousa.thamnyahapp.data.remote.api.SearchApiService
import com.mousa.thamnyahapp.data.remote.datasource.HomeSectionsDatasource
import com.mousa.thamnyahapp.data.remote.datasource.SearchDatasource
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
    fun provideSectionsDatasource(@MainApi apiService:HomeApiService): HomeSectionsDatasource =
        HomeSectionsDatasource(apiService)

    @Provides
    @Singleton
    fun provideSearchDatasource(@SearchApi apiService:SearchApiService): SearchDatasource =
        SearchDatasource(apiService)
}