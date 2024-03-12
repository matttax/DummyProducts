package com.matttax.dummyproducts.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Purple700,
    primaryContainer = Color.Black,
    secondaryContainer = Color.White,
    tertiaryContainer = SuperDarkGray,
    onPrimary = Color.White,
    onSecondary = Color.LightGray,
    onTertiary = Color.Gray,
    error = Color.Red
)

private val LightColorPalette = lightColorScheme(
    primary = Purple700,
    primaryContainer = Color.White,
    secondaryContainer = Color.Black,
    tertiaryContainer = SuperLightGray,
    onPrimary = Color.Black,
    onSecondary = Color.Gray,
    onTertiary = Color.White,
    error = Color.Red
)

@Composable
fun DummyProductsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
