package com.example.urlshortener.presentation.main_screen

import com.example.urlshortener.data.model.URL

data class MainScreenState (
    val isLoading: Boolean = false,
    val currentURL: URL? = null,
    val error: String = ""
)