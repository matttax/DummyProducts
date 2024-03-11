package com.matttax.dummyproducts.presentation.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween

object AnimationUtils {
    private const val DEFAULT_SLIDE_DURATION = 500
    private const val DEFAULT_FADE_DURATION = 350
    private const val DEFAULT_SCALE_DURATION = 400

    val scaleIn = scaleIn(
        animationSpec = tween(DEFAULT_SCALE_DURATION)
    )

    val scaleOut = scaleOut(
        animationSpec = tween(DEFAULT_SCALE_DURATION)
    )

    val popUpEnter = slideInVertically (
        initialOffsetY = { -it },
        animationSpec = tween(
            durationMillis = DEFAULT_SLIDE_DURATION,
            easing = LinearOutSlowInEasing
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = DEFAULT_FADE_DURATION,
            easing = LinearOutSlowInEasing
        )
    )

    val popUpExit = slideOutVertically (
        targetOffsetY = { -it },
        animationSpec = tween(
            durationMillis = DEFAULT_SLIDE_DURATION,
            easing = FastOutSlowInEasing
        )
    )  + fadeOut(
        animationSpec = tween(
            durationMillis = DEFAULT_FADE_DURATION,
            easing = FastOutSlowInEasing
        )
    )
}
