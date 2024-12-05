package com.imrul.task3samsung.feature_map.data

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class LocationManager(private val fusedLocationProviderClient: FusedLocationProviderClient) {
    private val _locationFlow = MutableSharedFlow<Location?>()
    val locationFlow: SharedFlow<Location?> = _locationFlow


}
