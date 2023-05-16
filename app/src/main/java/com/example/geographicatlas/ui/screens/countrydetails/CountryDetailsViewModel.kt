package com.example.geographicatlas.ui.screens.countrydetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.domain.model.CountryDetailsResultDomain
import com.example.geographicatlas.domain.model.CountryDomainModel
import com.example.geographicatlas.domain.usecase.FetchCountryDetailsUseCase
import com.example.geographicatlas.ui.adapter.DelegateItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryDetailsViewModel(
    private val fetchCountryDetailsUseCase: FetchCountryDetailsUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val countryDetails = MutableStateFlow<CountryDomainModel?>(null)
    private val loadingState = MutableStateFlow<Boolean>(false)
    private val errorState = MutableStateFlow<Throwable?>(null)
    private var isFetchedCountryDetails = false

    fun countryDetails(): StateFlow<CountryDomainModel?> = countryDetails

    fun loadingState(): StateFlow<Boolean> = loadingState

    fun errorState(): StateFlow<Throwable?> = errorState

    fun fetchCountryInfo(cca2: String) {
        if(!isFetchedCountryDetails){
            loadingState.value = true
            viewModelScope.launch(ioDispatcher){
                val result = fetchCountryDetailsUseCase(cca2)
                Log.e("TAG", "fetchCountryInfo: $result")
                when(result){
                    is CountryDetailsResultDomain.Fail -> {
                        errorState.value = result.error
                    }
                    is CountryDetailsResultDomain.Success -> {
                        countryDetails.value = result.countryDetails
                    }
                }
                isFetchedCountryDetails = true
                loadingState.value = false
            }
        }
    }

    fun resetErrorState(){
        errorState.value = null
    }


}