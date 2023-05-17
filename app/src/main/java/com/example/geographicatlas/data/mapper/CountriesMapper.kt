package com.example.geographicatlas.data.mapper

import com.example.geographicatlas.data.cache.model.CountryEntity
import com.example.geographicatlas.data.cloud.model.CountryInfoResponse
import com.example.geographicatlas.data.parser.ParseCountyItems
import com.example.geographicatlas.domain.model.CountryDomainModel

interface CountriesMapper {
    fun mapResponseToCache(countries: List<CountryInfoResponse>): List<CountryEntity>
    fun mapCacheToDomain(countries: List<CountryEntity>): List<CountryDomainModel>
    fun mapDomainToCache(country: CountryDomainModel): CountryEntity

    fun mapResponseToDomain(countries: List<CountryInfoResponse>): List<CountryDomainModel>
}

class CountriesMapperImpl(
    private val parser: ParseCountyItems,
) : CountriesMapper {

    override fun mapResponseToCache(countries: List<CountryInfoResponse>): List<CountryEntity> {
        return countries.map { countryResponse ->
            val capital = parser.parseCapital(countryResponse.capital)
            val continent = parser.parseContinent(countryResponse.continents)
            val currency = parser.parseCurrency(countryResponse.currencies)
            CountryEntity(
                id = 0,
                name = countryResponse.name.common,
                capital = capital,
                region = countryResponse.region,
                population = countryResponse.population,
                area = countryResponse.area,
                flag = countryResponse.flags.flag,
                isExpand = false,
                currency = currency,
                continent = continent,
                cca2 = countryResponse.cca2 ?: ""
            )
        }
    }

    override fun mapCacheToDomain(countries: List<CountryEntity>): List<CountryDomainModel> {
        return countries.map { countryEntity ->
            CountryDomainModel(
                id = countryEntity.id,
                name = countryEntity.name,
                capital = countryEntity.capital,
                region = countryEntity.region,
                population = countryEntity.population,
                area = countryEntity.area,
                flag = countryEntity.flag,
                isExpand = countryEntity.isExpand,
                currency = countryEntity.currency,
                continent = countryEntity.continent,
                cca2 = countryEntity.cca2,
            )
        }
    }

    override fun mapDomainToCache(country: CountryDomainModel): CountryEntity {
        return CountryEntity(
            id = country.id,
            name = country.name,
            capital = country.capital,
            region = country.region,
            population = country.population,
            area = country.area,
            flag = country.flag,
            isExpand = country.isExpand,
            currency = country.currency,
            continent = country.continent,
            cca2 = country.cca2,
        )
    }

    override fun mapResponseToDomain(countries: List<CountryInfoResponse>): List<CountryDomainModel> {
        return countries.map { countryResponse ->
            val capital = parser.parseCapital(countryResponse.capital)
            val continent = parser.parseContinent(countryResponse.continents)
            val currency = parser.parseCurrency(countryResponse.currencies)
            CountryDomainModel(
                id = 0,
                name = countryResponse.name.common,
                capital = capital,
                region = countryResponse.region,
                population = countryResponse.population,
                area = countryResponse.area,
                flag = countryResponse.flags.flag,
                isExpand = false,
                currency = currency,
                continent = continent,
                cca2 = countryResponse.cca2 ?: "",
                latLng = countryResponse.capitalInfo?.latLng?.joinToString(),
                timezones = countryResponse.timezones?.joinToString(separator = "\n"),
            )
        }
    }
}