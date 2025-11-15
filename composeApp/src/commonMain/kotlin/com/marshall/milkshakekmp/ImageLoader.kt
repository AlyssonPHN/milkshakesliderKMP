package com.marshall.milkshakekmp

import androidx.compose.runtime.Composable
import coil3.ImageLoader

/**
 * Declares an expected function to get a platform-specific ImageLoader.
 */
@Composable
expect fun getSvgImageLoader(): ImageLoader
