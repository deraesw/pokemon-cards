package com.deraesw.pokemoncards.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.data.domain.NetworkManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkManager: NetworkManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            networkManager.initialSync(complete = ::loadCompleted)
        }
    }

    private fun loadCompleted() {
        viewModelScope.launch {
            delay(3_000L)
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    data class MainState(
        val isLoading: Boolean = true,
    )
}
