package com.example.urlshortener.domain.use_case


import android.util.Log
import com.example.urlshortener.common.Status
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.domain.AddressFormatter
import com.example.urlshortener.domain.repository.URLrepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetShortURLuseCase @Inject constructor(
    private val repository: URLrepository,
    private val addressFormatter: AddressFormatter
) {

    suspend fun execute(address: String) = flow {
        try {
            emit(Status.Loading<String>())

            val formattedAddress = addressFormatter.format(address)
            val shortAddress = repository.getShortURL(formattedAddress)

            emit(Status.Success<String>(shortAddress))


        } catch(e: HttpException) {
            emit(Status.Error<String>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Status.Error<String>("Couldn't reach server. Check your internet connection."))
        }

    }




}