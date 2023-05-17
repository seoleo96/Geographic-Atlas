package com.example.geographicatlas.domain.model

data class CountryDomainModel(
    val id: Int,
    val name: String,
    val capital: String,
    val region: String,
    val population: Int,
    val area: Double,
    val flag: String,
    val isExpand: Boolean,
    val currency: String,
    val continent: String,
    val cca2: String,
    val latLng: String? = null,
    val timezones: String? = null,
)