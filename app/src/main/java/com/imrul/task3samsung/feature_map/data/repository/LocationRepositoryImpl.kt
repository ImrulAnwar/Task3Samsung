package com.imrul.task3samsung.feature_map.data.repository

import com.google.android.gms.location.FusedLocationProviderClient
import com.imrul.task3samsung.feature_map.domain.repository.LocationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class LocationRepositoryImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationRepository {
    override suspend fun getCurrentLocation() = flow {
        try {
            while (true) {
                val locationResult = fusedLocationProviderClient.lastLocation.await()
                emit(locationResult)
                delay(1000 * 60 * 10) // 10 min delay
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}