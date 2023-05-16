package com.example.geographicatlas

import android.app.Application
import androidx.room.Room
import com.example.geographicatlas.data.cache.CountriesDatabase
import com.example.geographicatlas.data.cache.dao.CountriesDao
import com.example.geographicatlas.data.cache.datasource.AtlasCacheDataSource
import com.example.geographicatlas.data.cache.datasource.AtlasCacheDataSourceImpl
import com.example.geographicatlas.data.cloud.GeographicalAtlasService
import com.example.geographicatlas.data.cloud.RetrofitInstance
import com.example.geographicatlas.data.cloud.datasource.AtlasCloudDataSource
import com.example.geographicatlas.data.cloud.datasource.AtlasCloudDataSourceImpl
import com.example.geographicatlas.data.mapper.CountriesMapper
import com.example.geographicatlas.data.mapper.CountriesMapperImpl
import com.example.geographicatlas.data.parser.ParseCountyItems
import com.example.geographicatlas.data.parser.ParseCountyItemsImpl
import com.example.geographicatlas.data.repository.AtlasRepositoryImpl
import com.example.geographicatlas.domain.repository.AtlasRepository
import com.example.geographicatlas.domain.usecase.*
import com.example.geographicatlas.ui.mapper.CountriesUiMapper
import com.example.geographicatlas.ui.mapper.CountriesUiMapperImpl
import com.example.geographicatlas.ui.screens.countrieslist.CountriesListViewModel
import com.example.geographicatlas.ui.screens.countrydetails.CountryDetailsViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoinMain()
    }

    private val appModule = module {
        single<GeographicalAtlasService> {
            RetrofitInstance().service()
        }
        single<CountriesDatabase> {
            Room.databaseBuilder(androidContext(), CountriesDatabase::class.java, "countries.db")
                .build()
        }
        single<CountriesDao> { get<CountriesDatabase>().countriesDao() }
        singleOf(::Gson)
        singleOf(::AtlasCacheDataSourceImpl) bind AtlasCacheDataSource::class
        singleOf(::AtlasCloudDataSourceImpl) bind AtlasCloudDataSource::class
        singleOf(::ParseCountyItemsImpl) bind ParseCountyItems::class
        singleOf(::CountriesMapperImpl) bind CountriesMapper::class
        singleOf(::AtlasRepositoryImpl) bind AtlasRepository::class
        singleOf(::FetchCountriesUseCaseImpl) bind FetchCountriesUseCase::class
        singleOf(::GetCountriesFlowUseCaseImpl) bind GetCountriesFlowUseCase::class
        singleOf(::UpdateCountryUseCaseImpl) bind UpdateCountryUseCase::class
        singleOf(::FetchCountryDetailsUseCaseImpl) bind FetchCountryDetailsUseCase::class
        single<CoroutineDispatcher> { Dispatchers.IO }
        singleOf(::CountriesUiMapperImpl) bind CountriesUiMapper::class
        viewModelOf(::CountriesListViewModel)
        viewModelOf(::CountryDetailsViewModel)
    }

    private fun startKoinMain() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}