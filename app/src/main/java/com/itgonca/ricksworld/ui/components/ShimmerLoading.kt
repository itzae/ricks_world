package com.itgonca.ricksworld.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape

@Composable
fun shimmerEffectBrush(): ShaderBrush {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer transition")
    val widthOfShadowBrush = 500
    val angleOfAxisY = 270f
    val durationMillis = 1500
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ), repeatMode = RepeatMode.Restart
        ),
        label = "shimmer animation offset"
    )
    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing
            ), repeatMode = RepeatMode.Restart
        ), label = "Shimmer loading"
    )
    return remember(offset) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = translateAnimation - widthOfShadowBrush
                val heightOffset = translateAnimation
                return LinearGradientShader(
                    colors = listOf(
                        Color.White.copy(alpha = 0.3f),
                        Color.White.copy(alpha = 0.5f),
                        Color.White.copy(alpha = 1.0f),
                        Color.White.copy(alpha = 0.5f),
                        Color.White.copy(alpha = 0.3f),
                    ),
                    from = Offset(x = widthOffset, y = 0.0f),
                    to = Offset(x = heightOffset, y = angleOfAxisY)
                )
            }
        }
    }
}

@Composable
fun Modifier.loading(isLoading: Boolean, shape: Shape = RectangleShape): Modifier =
    if (isLoading) {
        this
            .background(Color.LightGray, shape)
            .background(shimmerEffectBrush())
    } else this