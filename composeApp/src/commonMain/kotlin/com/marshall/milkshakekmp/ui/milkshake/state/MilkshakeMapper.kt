package com.marshall.milkshakekmp.ui.milkshake.state

import androidx.compose.ui.graphics.Color
import com.marshall.milkshakekmp.domain.model.Milkshake
import milkshakekmp.composeapp.generated.resources.Res
import milkshakekmp.composeapp.generated.resources.banhoney
import milkshakekmp.composeapp.generated.resources.chocoffe
import milkshakekmp.composeapp.generated.resources.cosmoberry
import milkshakekmp.composeapp.generated.resources.strawmilk
import org.jetbrains.compose.resources.DrawableResource

fun Milkshake.toUiModel(): MilkshakeUiModel {
    return MilkshakeUiModel(
        name = name,
        description = description,
        image = imageName.toDrawableResource(),
        color = Color(colorValue),
        price = price
    )
}

private fun String.toDrawableResource(): DrawableResource {
    return when (this) {
        "strawmilk" -> Res.drawable.strawmilk
        "chocoffe" -> Res.drawable.chocoffe
        "cosmoberry" -> Res.drawable.cosmoberry
        "banhoney" -> Res.drawable.banhoney
        else -> Res.drawable.strawmilk
    }
}
