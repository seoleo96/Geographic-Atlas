package com.example.geographicatlas.data.cloud.datasource

import com.example.geographicatlas.data.cloud.GeographicalAtlasService
import com.example.geographicatlas.data.cloud.model.AllCountriesResponse
import com.example.geographicatlas.data.cloud.model.CountriesResultData
import com.google.gson.Gson

interface AtlasCloudDataSource {
    suspend fun fetchCountries(): CountriesResultData
    suspend fun fetchCountryDetails(cca2: String): CountriesResultData
}

class AtlasCloudDataSourceImpl(
    private val service: GeographicalAtlasService,
    private val gson: Gson,
) : AtlasCloudDataSource {

    override suspend fun fetchCountries(): CountriesResultData {
        return try {
            val response = service.fetchCountries()
            if (response.isSuccessful) {
                val jsonData = gson.toJson(response.body())
                val custom = "{\"countries\":$jsonData}"
                val countryObj = gson.fromJson(custom, AllCountriesResponse::class.java)
                CountriesResultData.Success(countryObj.countries)
            } else {
                CountriesResultData.Fail(IllegalStateException())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            CountriesResultData.Fail(e)
        }
    }

    override suspend fun fetchCountryDetails(cca2: String): CountriesResultData {
        return try {
            val response = service.fetchCountryDetails(cca2)
            if (response.isSuccessful) {
                val jsonData = gson.toJson(response.body())
                val custom = "{\"countries\":$jsonData}"
                val countryObj = gson.fromJson(custom, AllCountriesResponse::class.java)
                CountriesResultData.Success(countryObj.countries)
            } else {
                CountriesResultData.Fail(IllegalStateException())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            CountriesResultData.Fail(e)
        }
    }

}