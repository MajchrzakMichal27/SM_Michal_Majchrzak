package com.example.wifisygnalanalizer.manager

import android.content.Context
import android.net.wifi.WifiManager
import com.example.wifisygnalanalizer.data.DistanceEstimator
import com.example.wifisygnalanalizer.data.WifiMeasurement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.pow

class WifiManagerHelper(
    private val context: Context,
    private val distanceEstimator: DistanceEstimator
) {
    private val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    /**
     * Pobiera kilka (count) szybkich odczytów z device'owego connectionInfo (przyrost czasu ~ bardzo mały).
     * Zwraca obiekt WifiMeasurement.
     */
    suspend fun takeMeasurement(samples: Int = 5, delayMsBetween: Long = 50L): WifiMeasurement? {
        return withContext(Dispatchers.Default) {
            try {
                val rssiSamples = mutableListOf<Int>()
                var lastInfo = wifiManager.connectionInfo
                // Pobieramy 'samples' razy — w praktyce connectionInfo.rssi szybko się nie zmienia,
                // ale to daje możliwość uśrednienia.
                repeat(samples) {
                    val info = wifiManager.connectionInfo
                    rssiSamples.add(info.rssi)
                    lastInfo = info
                    if (delayMsBetween > 0) Thread.sleep(delayMsBetween)
                }

                val distance = distanceEstimator.estimateDistanceFromRssiSamples(rssiSamples)

                WifiMeasurement(
                    timestamp = System.currentTimeMillis(),
                    ssid = lastInfo.ssid ?: "<unknown>",
                    rssi = rssiSamples.average().toInt(),
                    linkSpeedMbps = lastInfo.linkSpeed,
                    frequencyMHz = lastInfo.frequency,
                    distanceMeters = distance
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
