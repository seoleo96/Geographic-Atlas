package com.example.geographicatlas.data.repository

import com.example.geographicatlas.data.cache.datasource.AtlasCacheDataSource
import com.example.geographicatlas.data.cloud.datasource.AtlasCloudDataSource
import com.example.geographicatlas.data.cloud.model.CountriesResultData
import com.example.geographicatlas.data.mapper.CountriesMapper
import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.model.CountryDetailsResultDomain
import com.example.geographicatlas.domain.model.CountryDomainModel
import com.example.geographicatlas.domain.repository.AtlasRepository
import kotlinx.coroutines.flow.map

class AtlasRepositoryImpl(
    private val cloudDataSource: AtlasCloudDataSource,
    private val cacheDataSource: AtlasCacheDataSource,
    private val mapper: CountriesMapper,
) : AtlasRepository {

    override fun countriesFlow() = cacheDataSource.getAllFlow().map { countries ->
        mapper.mapCacheToDomain(countries)
    }

    override suspend fun fetchCountries(): CountriesResultDomain {
        return when (val result = cloudDataSource.fetchCountries()) {
            is CountriesResultData.Success -> {
                val cacheList = mapper.mapResponseToCache(result.countries)
                cacheDataSource.deleteAll()
                cacheDataSource.insert(cacheList)
                CountriesResultDomain.Success
            }
            is CountriesResultData.Fail -> {
                CountriesResultDomain.Fail(result.error)
            }
        }
    }

    override suspend fun fetchCountryDetails(cca2: String): CountryDetailsResultDomain {
        return when (val result = cloudDataSource.fetchCountryDetails(cca2)) {
            is CountriesResultData.Success -> {
                val countriesDetails = mapper.mapResponseToDomain(result.countries)
                if (countriesDetails.isNotEmpty()) {
                    CountryDetailsResultDomain.Success(countriesDetails[0])
                } else {
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