package com.imrul.task3samsung.feature_map.domain

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.imrul.task3samsung.feature_map.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AccessCurrentLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator suspend fun invoke(): Flow<Location> = repository.getCurrentLocation()
}