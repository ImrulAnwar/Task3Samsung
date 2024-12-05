package com.imrul.task3samsung.feature_map.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.imrul.task3samsung.feature_map.data.repository.LocationRepositoryImpl
import com.imrul.task3samsung.feature_map.domain.AccessCurrentLocationUseCase
import com.imrul.task3samsung.feature_map.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun provideUseCase(repository: LocationRepository) =
        AccessCurrentLocationUseCase(repository)

    @Provides
    fun provideRepository(fusedLocationProviderClient: FusedLocationProviderClient): LocationRepository =
        LocationRepositoryImpl(fusedLocationProviderClient)

}