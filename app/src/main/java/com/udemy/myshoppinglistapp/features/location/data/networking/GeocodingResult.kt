package com.udemy.myshoppinglistapp.features.location.data.networking

import com.google.gson.annotations.SerializedName

data class GeocodingResult(
    @SerializedName("formatted_address")
    val address: String,
)
