package com.example.urlshortener.domain.repository

import com.example.urlshortener.domain.model.URL
import kotlinx.coroutines.flow.MutableStateFlow

interface URLrepository {

    suspend fun getAllURLs(): MutableList<URL>

    suspend fun addURL(url: URL)

    suspend fun getShortURL(address: String): String


}