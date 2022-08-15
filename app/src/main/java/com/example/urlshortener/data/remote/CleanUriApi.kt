package com.example.urlshortener.data.remote

import com.example.urlshortener.data.remote.response.GetURLresponse
import retrofit2.http.*

interface CleanUriApi {
    @FormUrlEncoded
    @POST("/api/v1/shorten")
    suspend fun getShortURL(@Field("url") url: String): GetURLresponse

}