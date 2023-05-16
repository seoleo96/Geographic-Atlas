package com.example.geographicatlas.data.cloud

import com.example.geographicatlas.data.cloud.model.AllCountriesResponse
import retrofit2.Response
import retrofit2.http.*

interface GeographicalAtlasService {

    @Headers("Content-Type: application/json")
    @GET("v3.1/all")
    suspend fun fetchCountries(): Response<List<Any>>
}