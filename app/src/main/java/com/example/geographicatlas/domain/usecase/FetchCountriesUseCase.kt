package com.example.geographicatlas.domain.usecase

import android.util.Log
import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.repository.AtlasRepository

interface FetchCountriesUseCase {
    suspend operator fun invoke(): CountriesResultDomain
}

class FetchCountriesUseCaseImpl(
    private val repository: AtlasRepository,
) : FetchCountriesUseCase {
    override suspend fun invoke(): CountriesResultDomain {
        val data =  repository.fetchCountries()
        Log.e("TAG", "invoke: ", )
        return data
    }
}