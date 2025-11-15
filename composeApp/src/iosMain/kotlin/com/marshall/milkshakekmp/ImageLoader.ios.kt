package com.marshall.milkshakekmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.svg.SvgDecoder
import coil3.map.DrawableResourceMapper

/**
 * Provides the actual implementation of the SVG ImageLoader for the iOS platform.
 */
@Composable
actual fun getSvgImageLoader(): ImageLoader {
    return remember {
        ImageLoader.Builder()
            .components { 
                add(SvgDecoder.Factory())
                add(DrawableResourceMapper())
            }
            .build()
    }
}
