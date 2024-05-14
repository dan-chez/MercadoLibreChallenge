package com.danchez.mercadolibrechallenge.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = mercadoLibreColor,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
)

@Composable
fun MercadoLibreTheme(
    // TODO Implement dark theme
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content,
    )
}
