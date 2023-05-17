package com.example.geographicatlas.ui.screens.countrieslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.usecase.FetchCountriesUseCase
import com.example.geographicatlas.domain.usecase.GetCountriesFlowUseCase
import com.example.geographicatlas.domain.usecase.UpdateCountryUseCase
import com.example.geographicatlas.ui.adapter.DelegateItem
import com.example.geographicatlas.ui.adapter.countrieslist.CountriesListAdapterItem
import com.example.geographicatlas.ui.base.FragmentUtils.toWithContinents
import com.example.geographicatlas.ui.mapper.CountriesUiMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountriesListViewModel(
    private val fetchCountriesUseCase: FetchCountriesUseCase,
    private val getCountriesFlowUseCase: GetCountriesFlowUseCase,
    private val updateCountryUseCase: UpdateCountryUseCase,
    private val ioDispatcher: CoroutineDispatcher,
    private val mapper: CountriesUiMapper,
) : ViewModel() {

    private val countries = MutableStateFlow<List<DelegateItem>>(emptyList())
    private val loadingState = MutableStateFlow<Boolean>(false)
    private val errorState = MutableStateFlow<Throwable?>(null)
    private var isFetchedCountries = false

    init {
        launchOperations()
    }

    fun countries(): StateFlow<List<DelegateItem>> = countries

    fun loadingState(): StateFlow<Boolean> = loadingState

    fun errorState(): StateFlow<Throwable?> = errorState

    private fun launchOperations() {
        viewModelScope.launch(ioDispatcher) {
            fetchCountries()
            observeCountries()
        }
    }

    private suspend fun observeCountries() {
        getCountriesFlowUseCase().collect {
            countries.value = mapper.mapCountriesToUi(it).toWithContinents()
            if (isFetchedCountries) {
                loadingState.value = false
            }
        }
    }

    private suspend fun fetchCountries() {
        if (!isFetchedCountries) {
            loadingState.value = true
            val result = fetchCountriesUseCase()
            if (result is CountriesResultDomain.Fail) {
                errorState.value = result.error
            }
            isFetchedCountries = true
        }
    }

    fun updateCountry(country: CountriesListAdapterItem) {
        viewModelScope.launch(ioDispatcher) {
            updateCountryUseCase(mapper.mapCountryToDomain(country))
        }
    }

    fun resetErrorState() {
        errorState.value = null
    }
}