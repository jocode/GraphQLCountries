package com.example.graphqlcountries.domain

class GetCountryUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(countryCode: String) : DetailedCountry? {
        return countryClient.getCountry(countryCode)
    }

}