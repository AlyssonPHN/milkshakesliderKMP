package com.marshall.milkshakekmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import milkshakekmp.composeapp.generated.resources.Res
import milkshakekmp.composeapp.generated.resources.great_vibes_regular
import milkshakekmp.composeapp.generated.resources.montserrat_semibold

@OptIn(ExperimentalResourceApi::class)
val greatVibesFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.great_vibes_regular, FontWeight.Normal)
    )

@OptIn(ExperimentalResourceApi::class)
val montserratFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        // Para ter pesos de fonte diferentes, adicione os arquivos .ttf correspondentes
        // aos seus recursos e aponte para eles aqui.
        Font(Res.font.montserrat_semibold, FontWeight.W600),
        Font(Res.font.montserrat_semibold, FontWeight.W800),
        Font(Res.font.montserrat_semibold, FontWeight.W900)
    )
