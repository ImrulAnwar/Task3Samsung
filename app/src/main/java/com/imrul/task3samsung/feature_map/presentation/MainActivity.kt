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
import com.imrul.task3samsung.feature_map.presentation.screen_map.MapScreen
import com.imrul.task3samsung.ui.theme.Task3SamsungTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task3SamsungTheme {
                val dialogQueue = viewModel.visiblePermissionsDialogueQueue
                val multiplePermissionsLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions(),
                    onResult = { perms ->
                        permissionsToRequest.forEach { permission ->
                            viewModel.onPermissionResult(
                                permission = permission,
                                isGranted = perms[permission] == true
                            )
                        }
                    }
                )
                LaunchedEffect(Unit) {
                    multiplePermissionsLauncher.launch(permissionsToRequest)
                }
//                dialogQueue.reversed().forEach { permission ->
//                    PermissionDialog(
//                        permissionTextProvider = when (permission) {
//                            is Manifest.permission.POST_NOTIFICATIONS -> PostNotificationTextProvider()
//                            else -> return@forEach
//                        },
//                        isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
//                            permission
//                        ),
//                        onDismiss = viewModel::dismissDialog,
//                        onOkClick = {
//                            viewModel.dismissDialog()
//                            multiplePermissionsLauncher.launch(
//                                arrayOf(permission)
//                            )
//                        },
//                        onGotoAppSettingsClick = ::openAppSettings
//                    )
//                }
                MapScreen()
            }
        }
    }
}
