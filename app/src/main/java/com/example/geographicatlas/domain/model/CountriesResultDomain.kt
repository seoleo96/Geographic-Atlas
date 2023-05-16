package com.example.geographicatlas.domain.model

sealed class CountriesResultDomain {
    object Success : CountriesResultDomain()
    class Fail(val error: Throwable) : CountriesResultDomain()
}
