package com.example.graphqlcountries.domain

class GetCountryUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute() : List<SimpleCountry> {
        return countryClient.getCountries()
            .sortedBy { it.name }
    }
    
}