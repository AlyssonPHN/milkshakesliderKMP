package com.marshall.milkshakekmp.ui.milkshake.viewmodel

import com.marshall.milkshakekmp.data.repository.MilkshakeRepositoryImpl
import com.marshall.milkshakekmp.domain.use_case.GetMilkshakesUseCase
import com.marshall.milkshakekmp.ui.milkshake.state.MilkshakeState
import com.marshall.milkshakekmp.ui.milkshake.state.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MilkshakeViewModel {

    private val getMilkshakesUseCase = GetMilkshakesUseCase(MilkshakeRepositoryImpl())

    private val _uiState = MutableStateFlow(MilkshakeState())
    val uiState: StateFlow<MilkshakeState> = _uiState.asStateFlow()

    init {
        loadMilkshakes()
    }

    private fun loadMilkshakes() {
        _uiState.update { it.copy(isLoading = true) }
        val milkshakes = getMilkshakesUseCase().map { it.toUiModel() }
        _uiState.update {
            it.copy(
                isLoading = false,
                milkshakes = milkshakes,
                currentMilkshake = milkshakes.firstOrNull()
            )
        }
    }

    fun onPageChanged(page: Int) {
        val milkshakes = _uiState.value.milkshakes
        if (milkshakes.isNotEmpty()) {
            val realPage = page % milkshakes.size
            _uiState.update { it.copy(currentMilkshake = milkshakes[realPage]) }
        }
    }
}
