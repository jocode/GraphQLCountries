package com.example.graphqlcountries.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphqlcountries.domain.DetailedCountry
import com.example.graphqlcountries.domain.GetCountriesUseCase
import com.example.graphqlcountries.domain.GetCountryUseCase
import com.example.graphqlcountries.domain.SimpleCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            _state.update { it.copy(
                countries = getCountriesUseCase.execute(),
                isLoading = false
            ) }
        }
    }

    fun selectedCountry(countryCode: String) {
        viewModelScope.launch {
            _state.update { it.copy(
                selectedCountry = getCountryUseCase.execute(countryCode),
                isLoading = false
            ) }
        }
    }

    fun dismissCountryDialog() {
        _state.update { it.copy(
            selectedCountry = null
        ) }
    }


    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )

}