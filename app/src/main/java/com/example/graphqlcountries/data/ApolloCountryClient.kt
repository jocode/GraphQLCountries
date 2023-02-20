package com.example.graphqlcountries.data

import com.apollographql.apollo3.ApolloClient
import com.example.GetCountriesQuery
import com.example.GetCountryQuery
import com.example.graphqlcountries.domain.CountryClient
import com.example.graphqlcountries.domain.DetailedCountry
import com.example.graphqlcountries.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(GetCountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { country ->
                country.toSimpleCountry()
            } ?: emptyList()
    }

    override suspend fun getCountry(countryCode: String): DetailedCountry? {
        return apolloClient
            .query(GetCountryQuery(countryCode = countryCode))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }


}