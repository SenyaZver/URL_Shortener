package com.example.urlshortener.data.repository

import com.example.urlshortener.data.remote.DataProvider
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.domain.repository.URLrepository
import javax.inject.Inject

class URLrepositoryImpl @Inject constructor(private val dataProvider: DataProvider) : URLrepository {

    private var URL_list = mutableListOf<URL>()


    override suspend fun getAllURLs(): MutableList<URL> {
        return URL_list
    }

    override suspend fun addURL(url: URL) {
        URL_list.add(url)
    }

    override suspend fun getShortURL(address: String): String {
        return dataProvider.provideShortURL(address)
    }
}