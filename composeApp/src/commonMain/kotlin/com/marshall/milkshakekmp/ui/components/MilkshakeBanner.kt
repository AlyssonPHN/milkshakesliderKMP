package com.marshall.milkshakekmp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marshall.milkshakekmp.greatVibesFontFamily
import com.marshall.milkshakekmp.montserratFontFamily
import com.marshall.milkshakekmp.ui.components.TopSemicircleShape
import milkshakekmp.composeapp.generated.resources.Res
import milkshakekmp.composeapp.generated.resources.star
import org.jetbrains.compose.resources.painterResource

@Composable
fun MilkshakeBanner(color: Color, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(top = 60.dp)
                .width(maxWidth / 1.4f)
                .height(300.dp)
                .clip(TopSemicircleShape())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            color.copy(alpha = 0.25f),
                            color
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
}
