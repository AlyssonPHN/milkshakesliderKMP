package com.marshall.milkshakekmp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import milkshakekmp.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.absoluteValue
import milkshakekmp.composeapp.generated.resources.star

class TopSemicircleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width, size.height)
            lineTo(0f, size.height)
            lineTo(0f, size.height / 2)

            arcTo(
                rect = Rect(
                    left = 0f,
                    top = size.height / 2 - size.width / 2,
                    right = size.width,
                    bottom = size.height / 2 + size.width / 2
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )

            close()
        }
        return Outline.Generic(path)
    }
}

@Preview
@Composable
fun MilkshakeScreen() {
    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )
    val pricePagerState = rememberPagerState(
        initialPage = pagerState.currentPage,
        pageCount = { Int.MAX_VALUE }
    )
    LaunchedEffect(pagerState.currentPage) {
        pricePagerState.animateScrollToPage(
            page = pagerState.currentPage,
            animationSpec = tween(durationMillis = 600, easing = EaseInOut)
        )
    }

    val currentMilkshake = milkshakes[pagerState.currentPage % milkshakes.size]

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
                        0.5f to animatedColor, // Transparency removed as requested
                        1.0f to Color.White
                    )
                )
            )
    ) {
        // 1. The banner is drawn first, so it's at the back.
        BoxWithConstraints(modifier = Modifier.align(Alignment.TopCenter)) {
            Box(
                modifier = Modifier
                    .padding(top = 60.dp)
                    .width(maxWidth / 1.4f)
                    .height(300.dp)
                    .clip(TopSemicircleShape())
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                animatedColor.copy(alpha = 0.25f),
                                animatedColor
                            )
                        )
                    ),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 40.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.star),
                        contentDescription = "Magic",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Only This",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.W600,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Weekend",
                        color = Color.White,
                        fontSize = 60.sp,
                        fontFamily = greatVibesFontFamily,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                    )
                }
            }
        }

        val flingBehavior = PagerDefaults.flingBehavior(
            state = pagerState,
            snapAnimationSpec = tween(durationMillis = 600, easing = EaseInOut)
        )

        // 2. The Pager is drawn second, so it appears on top of the banner.
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
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

        // 3. The footer is drawn last, so it appears on top of everything.
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = animatedColor,
                    thickness = 0.6.dp
                )
                Image(
                    painter = painterResource(Res.drawable.star),
                    contentDescription = "Star",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(animatedColor)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = animatedColor,
                    thickness = 0.6.dp
                )
                Box(
                    modifier = Modifier
                        .width(170.dp)
                        .height(40.dp)
                        .border(
                            BorderStroke(1.dp, animatedColor),
                            RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    VerticalPager(
                        state = pricePagerState,
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        userScrollEnabled = false,
                    ) { page ->
                        val realPage = page % milkshakes.size
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$${milkshakes[realPage].price} EACH",
                                fontSize = 21.sp,
                                color = animatedColor,
                                fontWeight = FontWeight.W800,
                                fontFamily = montserratFontFamily
                            )
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = animatedColor,
                    thickness = 0.6.dp
                )
                Image(
                    painter = painterResource(Res.drawable.star),
                    contentDescription = "Star",
                    modifier = Modifier.size(18.dp),
                    colorFilter = ColorFilter.tint(animatedColor)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = animatedColor,
                    thickness = 0.6.dp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "BUY 1 GET 1 FREE",
                fontSize = 12.sp,
                color = animatedColor,
                fontWeight = FontWeight.W600,
                fontFamily = montserratFontFamily
            )
        }
    }
}
