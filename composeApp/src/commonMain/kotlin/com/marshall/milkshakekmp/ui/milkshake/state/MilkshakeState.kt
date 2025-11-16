package com.marshall.milkshakekmp.ui.milkshake.state

data class MilkshakeState(
    val milkshakes: List<MilkshakeUiModel> = emptyList(),
    val currentMilkshake: MilkshakeUiModel? = null,
    val isLoading: Boolean = true
)
