package com.example.geographicatlas.ui.screens.countrieslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.domain.model.CountriesResultDomain
import com.example.geographicatlas.domain.usecase.FetchCountriesUseCase
import com.example.geographicatlas.domain.usecase.GetCountriesFlowUseCase
import com.example.geographicatlas.ui.adapter.DelegateItem
import com.example.geographicatlas.ui.adapter.countrieslist.CountriesListAdapterItem
import com.example.geographicatlas.ui.base.FragmentUtils.toWithContinents
import com.example.geographicatlas.ui.mapper.CountriesUiMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CountriesListViewModel(
    private val fetchCountriesUseCase: FetchCountriesUseCase,
    private val getCountriesFlowUseCase: GetCountriesFlowUseCase,
    private val mapper: CountriesUiMapper,
) : ViewModel() {

    val countries = MutableStateFlow<List<DelegateItem>>(emptyList())

    init {
        Log.e(TAG, "INIT: ")
        viewModelScope.launch(Dispatchers.IO) {
            getCountriesFlowUseCase().collect {
                countries.value = mapper.mapCountriesToUi(it).toWithContinents()
            }
        }
    }

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchCountriesUseCase().let { result ->
                if (result is CountriesResultDomain.Success) {

                }
            }
        }
    }

    companion object {
        private const val TAG = "CountriesListViewModel"
    }

}