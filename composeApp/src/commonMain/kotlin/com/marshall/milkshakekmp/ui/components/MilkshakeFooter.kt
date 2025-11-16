package com.marshall.milkshakekmp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marshall.milkshakekmp.montserratFontFamily
import com.marshall.milkshakekmp.ui.milkshake.state.MilkshakeUiModel
import milkshakekmp.composeapp.generated.resources.Res
import milkshakekmp.composeapp.generated.resources.star
import org.jetbrains.compose.resources.painterResource

@Composable
fun MilkshakeFooter(
    pricePagerState: PagerState,
    milkshakes: List<MilkshakeUiModel>,
    animatedColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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
