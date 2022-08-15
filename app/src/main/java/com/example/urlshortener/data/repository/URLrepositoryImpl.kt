package com.example.urlshortener.data.repository

import com.example.urlshortener.data.remote.DataProvider
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.data.room.URLdao
import com.example.urlshortener.domain.repository.URLrepository
import dagger.assisted.AssistedInject
import javax.inject.Inject

class URLrepositoryImpl @Inject constructor(
    private val dataProvider: DataProvider,
    private val URLdao: URLdao
    ): URLrepository {



    override suspend fun getAllURLs(): MutableList<URL> {
        return URLdao.getAllURLs()
    }

    override suspend fun addURL(url: URL) {
        URLdao.insertURL(url)
    }

    override suspend fun getShortURL(address: String): String {
        val shortAddress = dataProvider.provideShortURL(address)
        addURL(URL(address, shortAddress))

        return shortAddress
    }

    override suspend fun clearRepository() {
        URLdao.clearDataBase()
    }
}