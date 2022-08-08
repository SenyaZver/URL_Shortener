package com.example.urlshortener.data

class DataProvider {
    //TODO temp solution
    suspend fun provideShortURL(url: String): String {
        return "www.shortlink.com"
    }

}