package com.example.graphqlcountries.domain

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(countryCode: String): DetailedCountry?
}