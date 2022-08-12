package com.example.urlshortener.domain.repository

import com.example.urlshortener.data.model.URL

interface URLrepository {

    suspend fun getAllURLs(): MutableList<URL>

    suspend fun addURL(url: URL)

    suspend fun getShortURL(address: String): String

    suspend fun clearRepository()


}