package com.example.wifisygnalanalizer.data

import kotlin.math.pow
import com.example.wifisygnalanalizer.data.WifiMeasurement
import kotlin.math.log10

interface DistanceEstimator {
    fun estimateDistanceFromRssiSamples(
        rssiSamples: List<Int>,
        frequencyMHz: Int
    ): Double
}


class Estimator : DistanceEstimator {

    override fun estimateDistanceFromRssiSamples(
        rssiSamples: List<Int>,
        frequencyMHz: Int
    ): Double {
        if (rssiSamples.isEmpty()) return -1.0

        val avgRssi = rssiSamples.average()
        val L = -avgRssi

        return 10.0.pow(
            (L + 27.55 - 20 * log10(frequencyMHz.toDouble())) / 20.0
        )
    }
}


