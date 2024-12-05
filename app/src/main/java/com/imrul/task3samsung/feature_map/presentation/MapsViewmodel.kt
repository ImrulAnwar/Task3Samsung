package com.imrul.task3samsung.feature_map.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.imrul.task3samsung.feature_map.domain.AccessCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewmodel @Inject constructor(
    private val accessCurrentLocationUseCase: AccessCurrentLocationUseCase
) : ViewModel() {
    var usernameText by mutableStateOf("")
        private set

    var currentCoordinates by mutableStateOf(
        LatLng(
            22.3752,
            91.8349
        )
    )
        private set

    fun updateCoordinates(latLng: LatLng) {
        currentCoordinates = latLng
    }
}