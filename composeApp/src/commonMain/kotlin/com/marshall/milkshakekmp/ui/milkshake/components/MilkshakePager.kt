package com.marshall.milkshakekmp.ui.milkshake.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.marshall.milkshakekmp.montserratFontFamily
import com.marshall.milkshakekmp.ui.milkshake.state.MilkshakeUiModel
import org.jetbrains.compose.resources.painterResource
import kotlin.math.absoluteValue

@Composable
fun MilkshakePager(
    pagerState: PagerState,
    milkshakes: List<MilkshakeUiModel>,
    modifier: Modifier = Modifier
) {
    val flingBehavior = PagerDefaults.flingBehavior(
        state = pagerState,
        snapAnimationSpec = tween(durationMillis = 600, easing = EaseInOut)
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp),
        contentPadding = PaddingValues(start = 20.dp, end = 180.dp),
        flingBehavior = flingBehavior
    ) { page ->
        val pageOffset = (
                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                ).absoluteValue

        val realPage = page % milkshakes.size
        val easedOffset = EaseInOut.transform(pageOffset.coerceIn(0f, 1f))

        val blurRadius = lerp(0.dp, 4.dp, easedOffset)

        Box(
            modifier = Modifier
                .zIndex(1f - easedOffset) // zIndex inside pager for item stacking
                .graphicsLayer {
                    val scale = lerp(1f, 0.6f, easedOffset)
                    scaleX = scale
                    scaleY = scale
                    alpha = lerp(1f, 0.85f, easedOffset)
                    translationY = lerp(0f, -150f, easedOffset)
                }
                .blur(radius = blurRadius)
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(milkshakes[realPage].image),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = milkshakes[realPage].name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W600,
                    color = milkshakes[realPage].color,
                    fontFamily = montserratFontFamily
                )
            }
        }
    }
}
