package com.marshall.milkshakekmp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.absoluteValue

@Preview
@Composable
fun MilkshakeScreen() {
    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,  // Start in the middle for looping
        pageCount = { Int.MAX_VALUE }
    )
    val currentMilkshake = milkshakes[pagerState.currentPage % milkshakes.size]

    // Slow down the color transition
    val animatedColor by animateColorAsState(
        targetValue = currentMilkshake.color,
        animationSpec = tween(durationMillis = 600, easing = EaseInOut)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.White,
                        0.5f to animatedColor.copy(alpha = 0.5f),
                        1.0f to Color.White
                    )
                )
            )
    ) {
        // Create a custom fling behavior with a slower animation
        val flingBehavior = PagerDefaults.flingBehavior(
            state = pagerState,
            snapAnimationSpec = tween(durationMillis = 600, easing = EaseInOut)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 20.dp, end = 180.dp), // Asymmetric padding
            flingBehavior = flingBehavior // Apply the custom fling behavior
        ) { page ->
            val pageOffset = (
                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            ).absoluteValue

            val realPage = page % milkshakes.size
            val easedOffset = EaseInOut.transform(pageOffset.coerceIn(0f, 1f))

            // Calculate blur based on the offset
            val blurRadius = lerp(0.dp, 4.dp, easedOffset)

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        // Make items further away smaller and more transparent
                        val scale = lerp(1f, 0.6f, easedOffset)
                        scaleX = scale
                        scaleY = scale
                        alpha = lerp(1f, 0.4f, easedOffset)
                    }
                    .blur(radius = blurRadius) // Apply the blur effect
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart // Align main item to the start
            ) {
                Image(
                    painter = painterResource(milkshakes[realPage].image),
                    contentDescription = null,
                    modifier = Modifier.size(350.dp) // Adjusted image size
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentMilkshake.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "$${currentMilkshake.price} EACH",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = currentMilkshake.color
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "BUY 1 GET 1 FREE",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}
