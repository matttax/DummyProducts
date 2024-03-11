package com.matttax.dummyproducts.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.matttax.dummyproducts.R

val jostBold = FontFamily(
    Font(R.font.jost_black),
    Font(R.font.jost_semi_bold)
)

val jostNormal = FontFamily(
    Font(R.font.jost_black),
    Font(R.font.jost_regular)
)

val jostMedium = FontFamily(
    Font(R.font.jost_medium)
)

val jostLight = FontFamily(
    Font(R.font.jost_light)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = jostBold,
        fontSize = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = jostMedium,
        fontSize = 24.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = jostMedium,
        fontSize = 16.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = jostBold,
        fontSize = 15.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = jostLight,
        fontSize = 15.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = jostNormal,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = jostLight,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = jostMedium,
        fontSize = 12.sp
    ),
)
