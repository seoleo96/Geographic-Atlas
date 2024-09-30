package com.example.geographicatlas.data.cloud

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

const val contentType = "Content-Type:  application/octet-stream"
interface TestService {

    @Headers(contentType)//v7
    @POST("get_info")
    suspend fun getInfo(@Body body: String): Response<String>

    @Headers(contentType)//v8.3
    @POST("x_report")
    suspend fun xReport(@Body body: String): Response<String>

    @Headers(contentType)//v8.3
    @POST("sale")
    suspend fun sale(@Body body: String): Response<String>

    @Headers(contentType)//v8.3
    @POST("check_shift")
    suspend fun checkShift(@Body body: String): Response<String>

    @Headers(contentType)//v8.3
    @POST("close_shift")
    suspend fun closeShift(@Body body: String): Response<String>

    @Headers(contentType)//v8.3
    @POST("open_shift")
    suspend fun openShift(@Body body: String): Response<String>

    @Headers(contentType)//v8.3
    @POST("deposit")
    suspend fun deposit(@Body body: String): Response<String>

    @Headers(contentType)//v8.3
    @POST("withdraw")
    suspend fun withdraw(@Body body: String): Response<String>
}