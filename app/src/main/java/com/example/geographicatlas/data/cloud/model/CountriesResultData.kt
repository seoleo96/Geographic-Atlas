package com.example.geographicatlas.data.cloud.model


sealed class CountriesResultData {
    class Success(val countries: List<CountryInfoResponse>) : CountriesResultData()
    class Fail(val error: Throwable) : CountriesResultData()
}