package com.example.wifisygnalanalizer.data

import kotlin.math.pow

interface DistanceEstimator {
    fun estimateDistanceFromRssiSamples(rssiSamples: List<Int>): Double
}

class Estimator(                    //-> najprostszy model
    private val L0: Double = -45.0,
    private val gamma: Double = 3.0
) : DistanceEstimator {

    override fun estimateDistanceFromRssiSamples(rssiSamples: List<Int>): Double {
        if (rssiSamples.isEmpty()) return -1.0
        val avgRssi = rssiSamples.average()
        val exponent = (avgRssi - L0) / (10.0 * gamma)
        return 10.0.pow(exponent)
    }
}
