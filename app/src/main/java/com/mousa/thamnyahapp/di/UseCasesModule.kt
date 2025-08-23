package com.mousa.thamnyahapp.di

import com.mousa.thamnyahapp.domain.repository.HomeRepository
import com.mousa.thamnyahapp.domain.repository.SearchRepository
import com.mousa.thamnyahapp.domain.usecase.GetHomeSectionsUseCase
import com.mousa.thamnyahapp.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetHomeSectionsUseCase(repository: HomeRepository): GetHomeSectionsUseCase =
        GetHomeSectionsUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchSectionsUseCase(repository: SearchRepository): SearchUseCase =
        SearchUseCase(repository)
}