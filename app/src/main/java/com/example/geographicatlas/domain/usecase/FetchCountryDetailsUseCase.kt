package com.example.geographicatlas.domain.usecase

import com.example.geographicatlas.domain.model.CountryDetailsResultDomain
import com.example.geographicatlas.domain.repository.AtlasRepository

interface FetchCountryDetailsUseCase {
    suspend operator fun invoke(cca2: String): CountryDetailsResultDomain
}

class FetchCountryDetailsUseCaseImpl(
    private val repository: AtlasRepository,
) : FetchCountryDetailsUseCase {
    override suspend fun invoke(cca2: String): CountryDetailsResultDomain {
        return repository.fetchCountryDetails(cca2)
    }
}