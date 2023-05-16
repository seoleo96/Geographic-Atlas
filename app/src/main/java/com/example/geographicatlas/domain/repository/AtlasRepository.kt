package com.example.geographicatlas.domain.repository

import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.model.CountryDomainModel
import kotlinx.coroutines.flow.Flow

interface AtlasRepository {
    suspend fun fetchCountries(): CountriesResultDomain
    fun countriesFlow() : Flow<List<CountryDomainModel>>
}