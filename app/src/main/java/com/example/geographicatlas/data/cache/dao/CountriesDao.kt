package com.example.geographicatlas.data.cache.dao

import androidx.room.*
import com.example.geographicatlas.data.cache.model.CountryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CountriesDao {

    @Query("SELECT * FROM countryEntity")
    suspend fun getAll(): List<CountryEntity>

    @Query("SELECT * FROM countryEntity")
    fun getAllFlow(): Flow<List<CountryEntity>>

    @Insert
    suspend fun insert(value: CountryEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: List<CountryEntity>)

    @Update
    suspend fun update(value: CountryEntity)

    @Query("DELETE FROM countryEntity")
    suspend fun deleteAll()
}