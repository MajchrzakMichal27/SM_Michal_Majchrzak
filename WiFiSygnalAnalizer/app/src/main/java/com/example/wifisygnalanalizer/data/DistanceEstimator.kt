package com.example.wifisygnalanalizer.data

import kotlin.math.pow

interface DistanceEstimator {
    /**
     * Przyjmuje listę ostatnich pomiarów RSSI (np. kilka odczytów) i zwraca oszacowaną odległość w metrach.
     * Implementację możesz później podmienić na wywołanie Twojego modelu.
     */
    fun estimateDistanceFromRssiSamples(rssiSamples: List<Int>): Double
}

/**
 * Fallback: prosty model log-distance. TYLKO do testów — zastąp potem Twoim modelem.
 */
class LogDistanceEstimator(
    private val A: Int = -45, // referencyjne RSSI @1m — dopasuj
    private val n: Double = 3.0
) : DistanceEstimator {
    override fun estimateDistanceFromRssiSamples(rssiSamples: List<Int>): Double {
        if (rssiSamples.isEmpty()) return -1.0
        val avgRssi = rssiSamples.average()
        return 10.0.pow((A - avgRssi) / (10 * n))
    }
}
