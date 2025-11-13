package com.marshall.milkshakekmp

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.abs
import kotlin.math.roundToInt

@Preview
@Composable
fun MilkshakeScreen() {
    var currentMilkshakeIndex by remember { mutableStateOf(0) }
    val currentMilkshake = milkshakes[currentMilkshakeIndex]
    val nextMilkshake = milkshakes[(currentMilkshakeIndex + 1) % milkshakes.size]

    val animatedColor by animateColorAsState(currentMilkshake.color)

    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    val density = LocalDensity.current.density

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedColor)
    ) {
        Image(
            painter = painterResource(nextMilkshake.image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(250.dp)
                .alpha(0.5f)
                .scale(0.8f)
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            scope.launch {
                                offsetX.snapTo(offsetX.value + dragAmount.x)
                                scale.snapTo(1f - (abs(offsetX.value) / (200 * density)) * 0.15f)
                            }
                        },
                        onDragEnd = {
                            scope.launch {
                                val screenWidth = 400 * density
                                if (abs(offsetX.value) > screenWidth / 3) {
                                    val targetX = if (offsetX.value > 0) screenWidth else -screenWidth
                                    offsetX.animateTo(targetX, animationSpec = tween(400))
                                    currentMilkshakeIndex = (currentMilkshakeIndex + 1) % milkshakes.size
                                    offsetX.snapTo(0f)
                                    scale.snapTo(1f)
                                } else {
                                    offsetX.animateTo(0f, animationSpec = tween(400))
                                    scale.animateTo(1f, animationSpec = tween(400))
                                }
                            }
                        }
                    )
                }
        ) {
            Image(
                painter = painterResource(currentMilkshake.image),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .graphicsLayer {
                        scaleX = scale.value
                        scaleY = scale.value
                    }
            )
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
