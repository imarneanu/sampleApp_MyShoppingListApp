package com.udemy.myshoppinglistapp.features.location.data.networking

data class GeocodingResponse(
    val results: List<GeocodingResult>,
    val status: String,
)
