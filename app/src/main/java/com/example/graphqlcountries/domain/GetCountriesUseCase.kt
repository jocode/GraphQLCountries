package com.example.graphqlcountries.domain

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(text : String = "") : List<SimpleCountry> {
        return countryClient.getCountries()
            .sortedBy { it.name }
            .filter { it.name.contains(text, true) }
    }
    
}