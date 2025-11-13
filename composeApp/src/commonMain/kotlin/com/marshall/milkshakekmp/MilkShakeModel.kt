package com.marshall.milkshakekmp

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import milkshakekmp.composeapp.generated.resources.*

data class MilkShakeModel(
    val image: DrawableResource,
    val name: String,
    val price: String,
    val color: Color
)

val milkshakes = listOf(
    MilkShakeModel(
        image = Res.drawable.banhoney,
        name = "Banhoney",
        price = "12.00",
        color = Color(0xFFFF8C00)
    ),
    MilkShakeModel(
        image = Res.drawable.strawmilk,
        name = "Strawmilk",
        price = "11.00",
        color = Color(0xFFFF0000)
    ),
    MilkShakeModel(
        image = Res.drawable.chocoffe,
        name = "Chocoffee",
        price = "15.00",
        color = Color(0xFF420E03)
    ),
    MilkShakeModel(
        image = Res.drawable.cosmoberry,
        name = "Cosmoberry",
        price = "10.00",
        color = Color(0xFF6116DA)
    )
)
