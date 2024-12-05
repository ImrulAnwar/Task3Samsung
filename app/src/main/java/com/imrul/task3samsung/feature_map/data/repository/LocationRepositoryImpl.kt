package com.imrul.task3samsung.feature_map.data.repository

import com.google.android.gms.location.FusedLocationProviderClient
import com.imrul.task3samsung.feature_map.domain.repository.LocationRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class LocationRepositoryImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient
):LocationRepository {

    override suspend fun getCurrentLocation() = flow {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation.await()
            emit(locationResult)
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}