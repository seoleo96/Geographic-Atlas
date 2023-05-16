package com.example.geographicatlas.data.cloud.model

import com.google.gson.annotations.SerializedName


data class AllCountriesResponse(
    @SerializedName("countries") val countries: List<CountryInfoResponse>,
)

data class CountryInfoResponse(
    @SerializedName("name") val name: CountryNameResponse,
    @SerializedName("capital") val capital: List<String>? = null,
    @SerializedName("region") val region: String,
    @SerializedName("population") val population: Int,
    @SerializedName("area") val area: Double,
    @SerializedName("flags") val flags: FlagsResponse,
    @SerializedName("currencies") val currencies: Any?,
    @SerializedName("continents") val continents: List<String>? = null,
    @SerializedName("cca2") val cca2: String? = null,
    @SerializedName("capitalInfo") val capitalInfo: CapitalInfoResponse? = null,
    @SerializedName("timezones") val timezones: List<String>? = null,
)

data class CountryNameResponse(
    @SerializedName("common") val common: String,
)

data class FlagsResponse(
    @SerializedName("png") val flag: String,
)

data class CapitalInfoResponse(
    @SerializedName("latlng") val latLng: List<Double>? = null,
)