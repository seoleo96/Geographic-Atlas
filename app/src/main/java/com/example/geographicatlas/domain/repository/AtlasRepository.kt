package com.example.geographicatlas.domain.repository

import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.model.CountryDomainModel
import com.example.geographicatlas.domain.model.CountryDetailsResultDomain
import kotlinx.coroutines.flow.Flow

interface AtlasRepository {
    suspend fun fetchCountries(): CountriesResultDomain
    fun countriesFlow() : Flow<List<CountryDomainModel>>
    suspend fun updateCountry(country : CountryDomainModel)
    suspend fun fetchCountryDetails(ccca2 : String): CountryDetailsResultDomain
}