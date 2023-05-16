package com.example.geographicatlas.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countryEntity")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String,
    val capital: String,
    val region: String,
    val population: Int,
    val area: Double,
    val flag: String,
    val isExpand : Boolean,
    val currency:String,
    val continent : String
)