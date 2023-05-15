package com.example.geographicatlas.data.cloud

import com.example.geographicatlas.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitInstance {

    companion object {
        private const val baseURL = "https://kinopoiskapiunofficial.tech/"
    }

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.HEADERS
        this.level = HttpLoggingInterceptor.Level.BASIC
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private fun apiKeyAsHeader(it: Interceptor.Chain) = it.proceed(
        it.request()
            .newBuilder()
            .addHeader("X-API-KEY", "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
            .build()
    )

    private val client: OkHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor {
                apiKeyAsHeader(it)
            }
            if (BuildConfig.DEBUG) {
                addInterceptor(interceptor)
            }
        }
        .build()

    @OptIn(ExperimentalSerializationApi::class)
    fun service(): GeographicalAtlasService {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()
            .create(GeographicalAtlasService::class.java)
    }

}