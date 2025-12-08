package com.example.wifisygnalanalizer.vm

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifisygnalanalizer.data.DistanceEstimator
import com.example.wifisygnalanalizer.data.WifiMeasurement
import com.example.wifisygnalanalizer.manager.WifiManagerHelper
import com.example.wifisygnalanalizer.util.MovingAverage
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WifiViewModel(
    private val context: Context,
    private val distanceEstimator: DistanceEstimator
) : ViewModel() {

    var hasPermission by mutableStateOf(false)
        private set

    fun setPermissionGranted(granted: Boolean) {
        hasPermission = granted
    }

    private val helper = WifiManagerHelper(context, distanceEstimator)

    private val _history = MutableStateFlow<List<WifiMeasurement>>(emptyList())
    val history: StateFlow<List<WifiMeasurement>> = _history

    // dodatkowe statystyki
    private val _minRssi = MutableStateFlow<Int?>(null)
    val minRssi: StateFlow<Int?> = _minRssi

    private val _maxRssi = MutableStateFlow<Int?>(null)
    val maxRssi: StateFlow<Int?> = _maxRssi

    private val movingAverage = MovingAverage(windowSize = 5)

    private var continuousJob: Job? = null

    fun addMeasurement() {
        viewModelScope.launch {
            val m = helper.takeMeasurement()
            m?.let {
                _history.value = _history.value + it
                updateStats(it)
            }
        }
    }

    private fun updateStats(m: WifiMeasurement) {
        val rssi = m.rssi
        _minRssi.value = minOf(_minRssi.value ?: rssi, rssi)
        _maxRssi.value = maxOf(_maxRssi.value ?: rssi, rssi)
        movingAverage.add(rssi.toDouble())
    }

    fun startContinuousSampling(intervalMs: Long = 10000L) {
        if (continuousJob != null) return // już działa
        continuousJob = viewModelScope.launch {
            while (true) {
                val m = helper.takeMeasurement()
                m?.let {
                    _history.value = _history.value + it
                    updateStats(it)
                }
                delay(intervalMs)
            }
        }
    }

    fun stopContinuousSampling() {
        continuousJob?.cancel()
        continuousJob = null
    }

    override fun onCleared() {
        super.onCleared()
        stopContinuousSampling()
    }
}
