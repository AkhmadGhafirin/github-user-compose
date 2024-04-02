package com.astro.test.akhmadghafirin.di

import com.astro.test.akhmadghafirin.data.api.ApiService
import com.astro.test.akhmadghafirin.data.repository.Repository
import com.astro.test.akhmadghafirin.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepositoryImpl(retrofit: Retrofit): Repository {
        return RepositoryImpl(retrofit.create(ApiService::class.java))
    }
}