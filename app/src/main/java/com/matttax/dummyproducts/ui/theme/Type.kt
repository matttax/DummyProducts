package com.matttax.dummyproducts.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
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
        fontFamily = jostNormal,
        fontSize = 16.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = jostLight,
        fontSize = 14.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = jostMedium,
        fontSize = 12.sp
    ),

    labelLarge = TextStyle(
        fontFamily = jostBold,
        fontSize = 15.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = jostMedium,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = jostLight,
        fontSize = 15.sp,
        textDecoration = TextDecoration.LineThrough
    ),
    bodyLarge = TextStyle(
        fontFamily = jostNormal,
        fontSize = 24.sp,
    ),
)
