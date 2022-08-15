package com.example.urlshortener.presentation.main_screen

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.urlshortener.common.Status
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.domain.use_case.GetShortURLuseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getShortURLuseCase: GetShortURLuseCase
): ViewModel() {
    private val _state = mutableStateOf(MainScreenState())
    val state : State<MainScreenState> = _state


    fun getShortURL(address: String) {
        CoroutineScope(Dispatchers.IO).launch {

            getShortURLuseCase.execute(address).collect {status ->
                when(status) {
                    is Status.Loading -> {
                        _state.value = MainScreenState(isLoading = true)
                    }
                    is Status.Success -> {
                        _state.value = MainScreenState(currentURL = URL(address, status.data))
                    }
                    is Status.Error -> {
                        val message = status.message ?: "Unexpected error occured"
                        _state.value = MainScreenState(isLoading = false, error = message)
                    }
                }
            }


        }
    }



}