package com.example.geographicatlas.domain.model

sealed class CountriesResultDomain {
    object Success : CountriesResultDomain()
    class Fail(val error: Throwable) : CountriesResultDomain()
}

sealed class CountryDetailsResultDomain {
    class Success(val countryDetails: CountryDomainModel) : CountryDetailsResultDomain()
    class Fail(val error: Throwable) : CountryDetailsResultDomain()
}
