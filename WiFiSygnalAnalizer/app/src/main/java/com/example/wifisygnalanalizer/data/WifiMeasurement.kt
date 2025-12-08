package com.example.wifisygnalanalizer.data

data class WifiMeasurement(
    val timestamp: Long,
    val ssid: String,
    val rssi: Int,
    val linkSpeedMbps: Int,
    val frequencyMHz: Int,
    val distanceMeters: Double
)
