package com.imrul.task3samsung.feature_map.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.imrul.task3samsung.feature_map.presentation.screen_map.MapScreen
import com.imrul.task3samsung.feature_map.presentation.screen_map.MapViewModel
import com.imrul.task3samsung.ui.theme.Task3SamsungTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val mapViewModel: MapViewModel by viewModels()
    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Task3SamsungTheme {
                var isLocationPermissionGranted by remember { mutableStateOf(false) }
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        if (isGranted) {
                            isLocationPermissionGranted = true
                        }
                    }
                )
                LaunchedEffect(Unit) {
                    if (!isLocationPermissionGranted) {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                }

                LaunchedEffect(isLocationPermissionGranted) {
                    if (isLocationPermissionGranted) {

                        mapViewModel.getCurrentLocation()
                    }
                }



                MapScreen()
            }
        }
    }
}
