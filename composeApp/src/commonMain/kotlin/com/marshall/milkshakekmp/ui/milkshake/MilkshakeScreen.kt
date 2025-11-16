package com.marshall.milkshakekmp.ui.milkshake

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.marshall.milkshakekmp.data.repository.MilkshakeRepositoryImpl
import com.marshall.milkshakekmp.domain.use_case.GetMilkshakesUseCase
import com.marshall.milkshakekmp.ui.milkshake.components.MilkshakeBanner
import com.marshall.milkshakekmp.ui.milkshake.components.MilkshakeFooter
import com.marshall.milkshakekmp.ui.milkshake.components.MilkshakePager
import com.marshall.milkshakekmp.ui.milkshake.viewmodel.MilkshakeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun MilkshakeScreen(viewModel: MilkshakeViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )
    val pricePagerState = rememberPagerState(
        initialPage = pagerState.currentPage,
        pageCount = { Int.MAX_VALUE }
    )
    LaunchedEffect(pagerState.currentPage) {
        viewModel.onPageChanged(pagerState.currentPage)
        pricePagerState.animateScrollToPage(
            page = pagerState.currentPage,
            animationSpec = tween(durationMillis = 600, easing = EaseInOut)
        )
    }

    val animatedColor by animateColorAsState(
        targetValue = uiState.currentMilkshake?.color ?: Color.White,
        animationSpec = tween(durationMillis = 600, easing = EaseInOut)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.White,
                        0.5f to animatedColor,
                        1.0f to Color.White
                    )
                )
            )
    ) {
        uiState.currentMilkshake?.let {
            MilkshakeBanner(
                color = animatedColor,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        MilkshakePager(
            pagerState = pagerState,
            milkshakes = uiState.milkshakes
        )

        MilkshakeFooter(
            pricePagerState = pricePagerState,
            milkshakes = uiState.milkshakes,
            animatedColor = animatedColor,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
private fun MilkshakeScreenPreview() {
    val useCase = GetMilkshakesUseCase(MilkshakeRepositoryImpl())
    val viewModel = MilkshakeViewModel(useCase)
    MilkshakeScreen(viewModel = viewModel)
}
