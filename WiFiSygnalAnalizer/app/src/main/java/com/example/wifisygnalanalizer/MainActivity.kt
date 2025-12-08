package com.example.wifisygnalanalizer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wifisygnalanalizer.data.Estimator
import com.example.wifisygnalanalizer.wyglad.WifiScreen
import com.example.wifisygnalanalizer.vm.WifiViewModel

class MainActivity : ComponentActivity() {

    private val wifiViewModel: WifiViewModel by viewModels {
        object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(WifiViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return WifiViewModel(this@MainActivity, Estimator()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.all { it.value }
        wifiViewModel.setPermissionGranted(granted)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hasPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        wifiViewModel.setPermissionGranted(hasPermission)

        setContent {
            WifiScreen(
                viewModel = wifiViewModel,
                requestPermissionLauncher = requestPermissionLauncher
            )
        }
    }
}
