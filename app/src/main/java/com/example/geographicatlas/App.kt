package com.example.geographicatlas

import android.app.Application
import com.example.geographicatlas.data.cloud.GeographicalAtlasService
import com.example.geographicatlas.data.cloud.RetrofitInstance
import com.example.geographicatlas.data.repository.AtlasRepository
import com.example.geographicatlas.data.repository.AtlasRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
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
//        singleOf(::CloudDataSourceImpl) bind CloudDataSource::class
//        single { FilmsDatabase.getDatabase(androidContext()).filmDao() }
        singleOf(::AtlasRepositoryImpl) bind AtlasRepository::class
//        viewModelOf(::MainViewModel)
//        viewModelOf(::FavouritesViewModel)
//        viewModelOf(::FilmInfoViewModel)
    }

    private fun startKoinMain() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(applicationContext)
            modules(appModule)
        }
    }
}