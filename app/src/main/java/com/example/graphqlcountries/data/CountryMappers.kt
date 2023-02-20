package com.example.graphqlcountries.data

import com.example.GetCountriesQuery
import com.example.GetCountryQuery
import com.example.graphqlcountries.domain.DetailedCountry
import com.example.graphqlcountries.domain.SimpleCountry

fun GetCountryQuery.Country.toDetailedCountry() = DetailedCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No capital",
    currency = currency ?: "No currency",
    languages = languages.mapNotNull { it.name },
    continent = continent.name
)

fun GetCountriesQuery.Country.toSimpleCountry() = SimpleCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No capital"
)