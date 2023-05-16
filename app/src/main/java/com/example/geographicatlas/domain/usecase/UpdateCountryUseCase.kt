package com.example.geographicatlas.domain.usecase

import com.example.geographicatlas.domain.model.CountryDomainModel
import com.example.geographicatlas.domain.repository.AtlasRepository

interface UpdateCountryUseCase {
    suspend operator fun invoke(country: CountryDomainModel)
}

class UpdateCountryUseCaseImpl(
    private val repository: AtlasRepository,
) : UpdateCountryUseCase {
    override suspend fun invoke(country: CountryDomainModel) {
        repository.updateCountry(country)
    }
}