package com.example.urlshortener.presentation.history_screen

import com.example.urlshortener.data.model.URL

data class HistoryScreenState (
        val isLoading: Boolean = false,
        val URL_list: MutableList<URL> = mutableListOf(),
        val error: String = ""
)