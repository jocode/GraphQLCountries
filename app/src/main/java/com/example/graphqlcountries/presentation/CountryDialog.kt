package com.example.graphqlcountries.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.graphqlcountries.domain.DetailedCountry

@Composable
fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier
) {
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = country.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = country.name, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Continent: " + country.continent)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Language(s): $joinedLanguages")
        }
    }
}