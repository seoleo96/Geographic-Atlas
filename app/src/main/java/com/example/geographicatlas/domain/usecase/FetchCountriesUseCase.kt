package com.example.geographicatlas.domain.usecase

import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.repository.AtlasRepository

interface FetchCountriesUseCase {
    suspend operator fun invoke(): CountriesResultDomain
}

class FetchCountriesUseCaseImpl(
    private val repository: AtlasRepository,
) : FetchCountriesUseCase {
    override suspend fun invoke(): CountriesResultDomain {
        return repository.fetchCountries()
    }
}