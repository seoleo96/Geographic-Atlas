package com.example.geographicatlas.data.repository

import android.util.Log
import com.example.geographicatlas.data.cache.datasource.AtlasCacheDataSource
import com.example.geographicatlas.data.cloud.datasource.AtlasCloudDataSource
import com.example.geographicatlas.data.cloud.model.CountriesResultData
import com.example.geographicatlas.data.mapper.CountriesMapper
import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.model.CountryDomainModel
import com.example.geographicatlas.domain.model.CountryDetailsResultDomain
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
                val cacheList = mapper.mapResponseToCache(result.countries)
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

    override suspend fun fetchCountryDetails(ccca2 : String): CountryDetailsResultDomain {
        Log.e(TAG, "fetchCountryDetails: ")
        return when (val result = cloudDataSource.fetchCountryDetails(ccca2)) {
            is CountriesResultData.Success -> {
                val countriesDetails = mapper.mapResponseToDomain(result.countries)
                if(countriesDetails.isNotEmpty()){
                    Log.e(TAG, "fetchCountryDetails: ${countriesDetails[0]}")
                    CountryDetailsResultDomain.Success(countriesDetails[0])
                }else{
                    CountryDetailsResultDomain.Fail(IllegalArgumentException())
                }
            }
            is CountriesResultData.Fail -> {
                CountryDetailsResultDomain.Fail(result.error)
            }
        }
    }

    override suspend fun updateCountry(country: CountryDomainModel) {
        mapper.mapDomainToCache(country).let { countryEntity ->
            cacheDataSource.update(countryEntity)
        }
    }
}