package com.absut.currencyconverter.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Extension function for [BigDecimal] that rounds the value to four decimal places.
 * @return A [BigDecimal] rounded to four decimal places using [RoundingMode.HALF_DOWN].
 */
fun BigDecimal.roundToFourDecimalPlaces(): BigDecimal = setScale(4, RoundingMode.HALF_DOWN)

/**
 * Extension function for [Long] that converts milliseconds to seconds.
 * @return The value in seconds.
 */
fun Long.toSeconds() = this / 1_000L

/**
 * Extension function for [Long] that converts seconds to milliseconds.
 * @return The value in milliseconds.
 */
fun Long.toMillis() = this * 1_000L

@Composable
fun Int.toDp(): Dp = (this / LocalDensity.current.density).dp