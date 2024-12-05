package com.imrul.task3samsung.feature_map.presentation.screen_map

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    viewmodel: MapViewModel = hiltViewModel()
) {
    val currentCoordinates = viewmodel.currentCoordinates
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(currentCoordinates.latitude, currentCoordinates.longitude), 15f
        )
    }

    // Ensure the camera position updates when coordinates change
    LaunchedEffect(currentCoordinates) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(
            LatLng(currentCoordinates.latitude, currentCoordinates.longitude), 15f
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                viewmodel.updateCoordinates(latLng)
            },
            properties = MapProperties(isMyLocationEnabled = false),
            content = {
                Marker(
                    state = MarkerState(
                        position = LatLng(
                            currentCoordinates.latitude,
                            currentCoordinates.longitude
                        )
                    ),
                    title = "Current Location",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED) // Optional: Custom icon
                )
            }
        )
        Text(
            text = "Coordinates: ${currentCoordinates.latitude}, ${currentCoordinates.longitude}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 16.sp
        )
    }
}
