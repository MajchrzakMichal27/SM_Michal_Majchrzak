package com.example.wifisygnalanalizer.util

class MovingAverage(private val windowSize: Int) {
    private val window = ArrayDeque<Double>()

    fun add(value: Double) {
        window.addLast(value)
        if (window.size > windowSize) {
            window.removeFirst()
        }
    }

    fun average(): Double {
        if (window.isEmpty()) return 0.0
        return window.sum() / window.size
    }
}
