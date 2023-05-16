package com.example.geographicatlas.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geographicatlas.data.cache.dao.CountriesDao
import com.example.geographicatlas.data.cache.model.CountryEntity


@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}