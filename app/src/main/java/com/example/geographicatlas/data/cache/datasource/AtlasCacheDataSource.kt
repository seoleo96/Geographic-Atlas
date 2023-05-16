package com.example.geographicatlas.data.cache.datasource

import com.example.geographicatlas.data.cache.dao.CountriesDao
import com.example.geographicatlas.data.cache.model.CountryEntity
import kotlinx.coroutines.flow.Flow

interface AtlasCacheDataSource {
    suspend fun getAll(): List<CountryEntity>
    fun getAllFlow(): Flow<List<CountryEntity>>
    suspend fun insert(value: CountryEntity)
    suspend fun insert(value: List<CountryEntity>)
    suspend fun update(value: CountryEntity)
    suspend fun deleteAll()
}

class AtlasCacheDataSourceImpl(
    private val dao: CountriesDao,
) : AtlasCacheDataSource {
    override suspend fun getAll(): List<CountryEntity> {
        return dao.getAll()
    }

    override fun getAllFlow(): Flow<List<CountryEntity>> {
        return dao.getAllFlow()
    }

    override suspend fun insert(value: CountryEntity) {
        dao.insert(value)
    }

    override suspend fun insert(value: List<CountryEntity>) {
        dao.insert(value)
    }

    override suspend fun update(value: CountryEntity) {
        dao.update(value)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}