package com.example.geographicatlas.data.cloud

import com.example.geographicatlas.data.cloud.model.AllCountriesResponse
import retrofit2.Response
import retrofit2.http.*

interface GeographicalAtlasService {

    @GET("v3.1/all")
    suspend fun fetchCountries(): Response<List<Any>>

    @GET("v3.1/alpha/{cca2}")
    suspend fun fetchCountryDetails(
        @Path("cca2") cca2: String,
    ): Response<List<Any>>
}