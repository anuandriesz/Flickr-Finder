package com.assignment.flickerfinder.di

import com.assignment.flickerfinder.data.FlickerSearchRepository
import com.assignment.flickerfinder.network.api.FlickerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun provideFlickerSearchRepository (flickerApi: FlickerApi) : FlickerSearchRepository {
        return FlickerSearchRepository(flickerApi)
    }
}