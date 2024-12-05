package com.imrul.task3samsung.feature_map.presentation.screen_map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.imrul.task3samsung.feature_map.domain.AccessCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val accessCurrentLocationUseCase: AccessCurrentLocationUseCase
) : ViewModel() {
    private var currentJob: Job? = null

    init {
        getCurrentLocation()
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

    fun getCurrentLocation() {
        currentJob?.cancel()
        currentJob = viewModelScope.launch {
            accessCurrentLocationUseCase().collect { location ->
                val latLng = LatLng(location.latitude, location.longitude)
                currentCoordinates = latLng
            }
        }
    }
}