package com.deraesw.pokemoncards.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deraesw.pokemoncards.core.data.domain.NetworkManager
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkManager: NetworkManager,
) : ViewModel() {
    init {
        println("MainViewModel init")
        viewModelScope.launch {
            networkManager.initialSync()
        }
    }
}
