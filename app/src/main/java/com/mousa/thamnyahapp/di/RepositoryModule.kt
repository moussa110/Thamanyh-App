package com.mousa.thamnyahapp.di

import com.mousa.thamnyahapp.data.repository.HomeRepositoryImpl
import com.mousa.thamnyahapp.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository

}