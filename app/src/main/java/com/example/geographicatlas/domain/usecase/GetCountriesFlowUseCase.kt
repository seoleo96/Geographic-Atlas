package com.example.geographicatlas.domain.usecase

import com.example.geographicatlas.domain.model.CountryDomainModel
import com.example.geographicatlas.domain.repository.AtlasRepository
import kotlinx.coroutines.flow.Flow

interface GetCountriesFlowUseCase {
    suspend operator fun invoke(): Flow<List<CountryDomainModel>>
}

class GetCountriesFlowUseCaseImpl(
    private val repository: AtlasRepository,
) : GetCountriesFlowUseCase {
    override suspend fun invoke(): Flow<List<CountryDomainModel>> {
        return repository.countriesFlow()
    }
}