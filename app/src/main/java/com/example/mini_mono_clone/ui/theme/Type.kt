package com.example.mini_mono_clone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mini_mono_clone.R

val sfProText = FontFamily(
    listOf(
        Font(R.font.sfprotext_light, FontWeight.Light),
        Font(R.font.sfprotext_regular, FontWeight.Normal),
        Font(R.font.sfprotext_medium, FontWeight.Medium),
        Font(R.font.sfprotext_semibold, FontWeight.SemiBold),
        Font(R.font.sfprotext_bold, FontWeight.Bold)
    )
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = sfProText,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    )
)