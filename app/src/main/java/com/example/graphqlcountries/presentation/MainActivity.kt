package com.example.graphqlcountries.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.graphqlcountries.presentation.ui.theme.GraphQLCountriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLCountriesTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    CountriesScreen(
                        state = state,
                        onCountrySelected = viewModel::selectedCountry,
                        onDismissCountryDialog = viewModel::dismissCountryDialog,
                        onSearchTextChanged = viewModel::searchTextChanged,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}