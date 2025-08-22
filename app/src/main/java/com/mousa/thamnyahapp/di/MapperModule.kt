package com.mousa.thamnyahapp.di

import com.mousa.thamnyahapp.data.mappers.ContentMapper
import com.mousa.thamnyahapp.data.mappers.HomeMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    
    @Provides
    @Singleton
    fun provideContentMapper(): ContentMapper {
        return ContentMapper()
    }
    
    @Provides
    @Singleton
    fun provideHomeMapper(contentMapper: ContentMapper): HomeMapper {
        return HomeMapper(contentMapper)
    }
}