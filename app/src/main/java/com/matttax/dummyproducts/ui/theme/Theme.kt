package com.matttax.dummyproducts.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = Color.Black,
    secondary = Color.Gray,
    primaryContainer = Color.LightGray
)

private val LightColorPalette = lightColorScheme(
    primary = Purple700,
    primaryContainer = Color.White,
    secondaryContainer = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.Gray,
    onTertiary = Color.White,
    error = Color.Red,
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
