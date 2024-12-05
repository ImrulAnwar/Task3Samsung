package com.imrul.task3samsung.feature_map.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.imrul.task3samsung.feature_map.domain.AccessCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewmodel @Inject constructor(
    private val accessCurrentLocationUseCase: AccessCurrentLocationUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            while (true) {
                getCurrentLocation()
                delay(1000 * 60 * 10) // 10 min delay
//                delay(1000)
            }
        }
    }

    var currentCoordinates by mutableStateOf(
        LatLng(
            92.3752,
            91.8349
        )
    )
        private set

    fun updateCoordinates(latLng: LatLng) {
        currentCoordinates = latLng
    }

    private suspend fun getCurrentLocation() {
        accessCurrentLocationUseCase().collect { location ->
            val latLng = LatLng(location.latitude, location.longitude)
            currentCoordinates = latLng
        }
    }
}