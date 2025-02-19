package com.udemy.myshoppinglistapp.features.location.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udemy.myshoppinglistapp.BuildConfig
import com.udemy.myshoppinglistapp.core.data.networking.RetrofitClient
import com.udemy.myshoppinglistapp.features.location.data.networking.GeocodingResult
import com.udemy.myshoppinglistapp.features.location.domain.model.LocationData
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _addresses = mutableStateOf(listOf<GeocodingResult>())
    val addresses: State<List<GeocodingResult>> = _addresses

    fun updateLocation(newLocation: LocationData) {
        _location.value = newLocation
    }

    fun fetchAddress(latlng: String) {
        println("fetchAddress")
        try {
            viewModelScope.launch {
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latlng,
                    apiKey = BuildConfig.MAPS_API_KEY,
                )
                println("result: ${result.results.size}")
                _addresses.value = result.results
                println("address: ${_addresses.value.firstOrNull()}")
            }
        } catch (e: Exception) {
            Log.d("LocationViewModel", "Error fetching address: ${e.cause} ${e.message}")
        }
    }
}
