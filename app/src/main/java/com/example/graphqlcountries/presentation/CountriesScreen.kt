package com.example.graphqlcountries.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.graphqlcountries.domain.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountriesState,
    onCountrySelected: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
    onSearchTextChanged: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            TextField(
                value = state.searchQuery,
                onValueChange = onSearchTextChanged,
                placeholder = { Text(text = "Search") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        modifier = Modifier.clickable {
                            onSearchTextChanged("")
                            focusManager.clearFocus()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCountrySelected(country.code)
                            }
                            .padding(16.dp),
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        if (state.selectedCountry != null) {
            CountryDialog(
                country = state.selectedCountry,
                onDismiss = onDismissCountryDialog,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = country.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = country.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = country.capital,
                fontSize = 16.sp
            )
        }
    }
}