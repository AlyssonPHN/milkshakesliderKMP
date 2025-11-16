package com.marshall.milkshakekmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.marshall.milkshakekmp.ui.milkshake.MilkshakeScreen
import org.koin.compose.koinInject

@Composable
fun App() {
    MaterialTheme {
        MilkshakeScreen(viewModel = koinInject())
    }
}
