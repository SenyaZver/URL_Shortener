package com.example.urlshortener.presentation.main_screen

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.urlshortener.common.Status
import com.example.urlshortener.data.model.URL
import com.example.urlshortener.domain.use_case.GetShortURLuseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getShortURLuseCase: GetShortURLuseCase
): ViewModel() {
    private val _state = mutableStateOf(MainScreenState())
    val state : State<MainScreenState> = _state


    fun getShortURL(address: String) {
        viewModelScope.launch {

            getShortURLuseCase.execute(address).collect {result ->
                when(result) {
                    is Status.Loading -> {
                        _state.value = MainScreenState(isLoading = true)
                    }
                    is Status.Success -> {
                        _state.value = MainScreenState(currentURL = URL(address, result.data))
                    }
                    is Status.Error -> {
                        val message = result.message ?: "Unexpected error occured"
                        _state.value = MainScreenState(isLoading = false, error = message)
                    }
                }
            }


        }
    }



}