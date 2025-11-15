package com.marshall.milkshakekmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.svg.SvgDecoder

/**
 * Provides the actual implementation of the SVG ImageLoader for the Android platform.
 */
@Composable
actual fun getSvgImageLoader(): ImageLoader {
    return ImageLoader.Builder(LocalContext.current)
        .components { 
            add(SvgDecoder.Factory())
        }
        .build()
}
