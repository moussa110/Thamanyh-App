package com.mousa.thamnyahapp.di

import com.mousa.thamnyahapp.domain.repository.HomeRepository
import com.mousa.thamnyahapp.domain.usecase.GetHomeSectionsUseCase
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
}