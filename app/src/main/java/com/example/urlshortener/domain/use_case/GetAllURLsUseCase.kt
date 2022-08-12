package com.example.urlshortener.domain.use_case

import com.example.urlshortener.common.Status
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.domain.repository.URLrepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllURLsUseCase @Inject constructor(private val repository: URLrepository) {

    suspend fun execute() = flow {
        emit(Status.Loading<MutableList<URL>>())

        val URL_list = repository.getAllURLs()

        emit(Status.Success<MutableList<URL>> (URL_list))

    }

}