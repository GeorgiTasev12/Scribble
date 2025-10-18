package com.georgitasev.scribble.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFAAB5F5),
    inversePrimary = Color(0xFF262C3D),
    secondary = Color(0xFFAAA1DF),
    onPrimary = Color(0xFFEBEAEA),
    onSecondary = Color(0xFF212121),
    primaryContainer = Color(0xFF474F81),
    onPrimaryContainer = Color(0xFFF5F5F5),
    errorContainer = Color(0xFF877E98)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4574EC),
    inversePrimary = Color(0xFFCFD8EF),
    secondary = Color(0xFF280AE5),
    onPrimary = Color(0xFF161616),
    onSecondary = Color(0xFFE5E5E5),
    primaryContainer = Color(0xB3AEB4C7),
    onPrimaryContainer = Color(0xFF161616)
)

@Composable
fun ScribbleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}