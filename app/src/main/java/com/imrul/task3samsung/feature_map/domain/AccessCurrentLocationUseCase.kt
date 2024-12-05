package com.imrul.task3samsung.feature_map.domain

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AccessCurrentLocationUseCase @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {
    operator fun invoke(): Flow<Location> = flow {
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