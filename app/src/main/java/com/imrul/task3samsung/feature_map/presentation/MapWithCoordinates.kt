package com.imrul.task3samsung.feature_map.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapWithCoordinates() {
    var currentCoordinates by remember { mutableStateOf(LatLng(22.3752,  91.8349)) } // Default to San Francisco

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(currentCoordinates, 10f)
            },
            onMapClick = { latLng ->
                currentCoordinates = latLng
            }
        )
        Text(
            text = "Coordinates: ${currentCoordinates.latitude}, ${currentCoordinates.longitude}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
        )
    }
}
