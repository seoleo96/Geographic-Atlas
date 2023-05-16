package com.example.geographicatlas.data.repository

import android.util.Log
import com.example.geographicatlas.data.cache.datasource.AtlasCacheDataSource
import com.example.geographicatlas.data.cloud.datasource.AtlasCloudDataSource
import com.example.geographicatlas.data.cloud.model.CountriesResultData
import com.example.geographicatlas.data.mapper.CountriesMapper
import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.repository.AtlasRepository
import kotlinx.coroutines.flow.map

class AtlasRepositoryImpl(
    private val cloudDataSource: AtlasCloudDataSource,
    private val cacheDataSource: AtlasCacheDataSource,
    private val mapper: CountriesMapper,
) : AtlasRepository {

    companion object {
        private const val TAG = "AtlasRepositoryImpl"
    }

    override fun countriesFlow() = cacheDataSource.getAllFlow().map { countries ->
        mapper.mapCacheToDomain(countries)
    }

    override suspend fun fetchCountries(): CountriesResultDomain {
        Log.e(TAG, "fetchCountries: ")
        return when (val result = cloudDataSource.fetchCountries()) {
            is CountriesResultData.Success -> {
                Log.e(TAG, "fetchCountries: before map")
                val cacheList =  mapper.mapResponseToCache(result.countries)
                cacheDataSource.deleteAll()
                cacheDataSource.insert(cacheList)
                Log.e(TAG, "fetchCountries: RETURN")
                CountriesResultDomain.Success
            }
            is CountriesResultData.Fail -> {
                CountriesResultDomain.Fail(result.error)
            }
        }
    }
}