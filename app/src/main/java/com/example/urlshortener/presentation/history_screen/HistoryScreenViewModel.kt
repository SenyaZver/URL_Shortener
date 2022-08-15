package com.example.urlshortener.presentation.history_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.urlshortener.common.Status
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.domain.use_case.GetAllURLsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HistoryScreenViewModel @Inject constructor(
    private val getAllURLsUseCase: GetAllURLsUseCase
): ViewModel()  {
    private val _state = mutableStateOf(HistoryScreenState())
    val state: State<HistoryScreenState> = _state


    init {
        getAllURLs()
    }

    private fun getAllURLs() {
        CoroutineScope(Dispatchers.IO).launch {
            getAllURLsUseCase.execute().collect {status ->

                withContext(Dispatchers.Main) {
                    when (status) {
                        is Status.Loading -> {
                            _state.value = HistoryScreenState(isLoading = true)
                        }
                        is Status.Success -> {
                            _state.value = HistoryScreenState(URL_list = status.data ?: mutableListOf<URL>())
                        }
                        is Status.Error -> {
                            _state.value = HistoryScreenState(error = "An unexpected error occured")
                        }
                    }
                }



            }

        }
    }



}