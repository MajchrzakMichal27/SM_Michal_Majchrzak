package com.example.wifisygnalanalizer.wyglad

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wifisygnalanalizer.vm.WifiViewModel
import java.text.SimpleDateFormat
import java.util.*
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun WifiScreen(viewModel: WifiViewModel, requestPermissionLauncher: androidx.activity.result.ActivityResultLauncher<Array<String>>) {
    val history by viewModel.history.collectAsState()
    val minRssi by viewModel.minRssi.collectAsState()
    val maxRssi by viewModel.maxRssi.collectAsState()
    val hasPermission = viewModel.hasPermission

    val context = LocalContext.current

    var isFirstRequest by remember { mutableStateOf(true) }

    if (!hasPermission) {
        val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            context as Activity,
            Manifest.permission.ACCESS_FINE_LOCATION)

        if (!shouldShowRationale && isFirstRequest) {
            PermissionRequestScreen(
                onRequestPermission = {
                    isFirstRequest = false
                    requestPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            )
        } else if (shouldShowRationale) {
            PermissionRequestScreen(
                onRequestPermission = {
                    requestPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            )
        } else {
            GoToSettings()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { viewModel.addMeasurement() }) {
                Text("Dodaj pomiar")
            }
            Button(onClick = { viewModel.startContinuousSampling(10000L) }) {
                Text("Start (10s)")
            }
            Button(onClick = { viewModel.stopContinuousSampling() }) {
                Text("Stop")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Min RSSI: ${minRssi ?: "-"}")
        Text("Max RSSI: ${maxRssi ?: "-"}")

        Spacer(modifier = Modifier.height(12.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Historia pomiarów",
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(history) { m ->
                MeasurementItem(m)
                Divider()
            }
        }
    }
}

@Composable
fun MeasurementItem(m: com.example.wifisygnalanalizer.data.WifiMeasurement) {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Time: ${sdf.format(Date(m.timestamp))}")
        Text("SSID: ${m.ssid}")
        Text("RSSI: ${m.rssi} dBm")
        Text("Distance: ${"%.2f".format(m.distanceMeters)} m")
        Text("Link speed: ${m.linkSpeedMbps} Mbps, Freq: ${m.frequencyMHz} MHz")
    }
}

@Composable
fun PermissionRequestScreen(onRequestPermission: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Aby odczytać poziom sygnału WiFi, aplikacja potrzebuje uprawnienia do lokalizacji.",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRequestPermission) {
            Text("Przyznaj uprawnienia")
        }
    }
}

@Composable
fun GoToSettings() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Odrzuciłeś opcje udostępniania lokalizacji" +
                    "Aby odczytać poziom sygnału WiFi, aplikacja potrzebuje uprawnienia do lokalizacji."+
                    "Aby aplikacja mogła działać, przyznaj je w ustawieniach.",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            context.startActivity(intent)
        }) {
            Text("Otwórz ustawienia")
        }
    }
}

