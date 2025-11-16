package com.marshall.milkshakekmp.ui.milkshake.state

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class MilkshakeUiModel(
    val name: String,
    val description: String,
    val image: DrawableResource,
    val color: Color,
    val price: Double
)
