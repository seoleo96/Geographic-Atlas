package com.example.geographicatlas.ui.mapper

import com.example.geographicatlas.domain.model.CountryDomainModel
import com.example.geographicatlas.ui.adapter.countrieslist.CountriesListAdapterItem

interface CountriesUiMapper {
    fun mapCountriesToUi(countries: List<CountryDomainModel>): List<CountriesListAdapterItem>
    fun mapCountryToDomain(country: CountriesListAdapterItem): CountryDomainModel
}

class CountriesUiMapperImpl : CountriesUiMapper {

    override fun mapCountriesToUi(countries: List<CountryDomainModel>): List<CountriesListAdapterItem> {
        return countries.map { country ->
            CountriesListAdapterItem(
                id = country.id,
                name = country.name,
                capital = country.capital,
                region = country.region,
                population = country.population,
                area = country.area,
                flag = country.flag,
                isExpand = country.isExpand,
                currency =  country.currency,
                continent =  country.continent,
                cca2 =  country.cca2,
            )
        }
    }

    override fun mapCountryToDomain(country: CountriesListAdapterItem): CountryDomainModel {
        return CountryDomainModel(
            id = country.id,
            name = country.name,
            capital = country.capital,
            region = country.region,
            population = country.population,
            area = country.area,
            flag = country.flag,
            isExpand = country.isExpand,
            currency =  country.currency,
            continent =  country.continent,
            cca2 =  country.cca2,
        )
    }
}