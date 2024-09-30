package com.example.geographicatlas.data.cloud

import com.example.geographicatlas.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object {
        private const val BASE_URL = "https://restcountries.com/"
    }

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.HEADERS
        this.level = HttpLoggingInterceptor.Level.BASIC
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(interceptor)
                connectTimeout(1, TimeUnit.MINUTES)
                readTimeout(1, TimeUnit.MINUTES)
                writeTimeout(1, TimeUnit.MINUTES)
        }
        .build()

    fun service(): GeographicalAtlasService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GeographicalAtlasService::class.java)
    }

    fun serviceTest(): TestService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.3.177:8008/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TestService::class.java)
    }
}