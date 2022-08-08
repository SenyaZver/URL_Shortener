package com.example.urlshortener.data.remote

class DataProvider(private val api: CleanUriApi) {

    suspend fun provideShortURL(url: String): String {
        val response = api.getShortURL(url)

        if (response.error != null) {
            return response.result_url
        }
        return response.result_url
    }

}