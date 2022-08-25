package com.egbuna.autocheck.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

const val BASE_URL = "https://api-prod.autochek.africa/v1/"

fun Double.getDecoratedStringFromNumber(currencySymbol: String): String {
    val numberPattern = "#,###,###,###"
    val formatter = DecimalFormat.getInstance(Locale.getDefault()) as DecimalFormat
    formatter.applyPattern("$currencySymbol$numberPattern")
    return formatter.format(this)
}

fun Long.getDecoratedStringFromNumber(currencySymbol: String): String {
    val numberPattern = "#,###,###,###"
    val formatter = DecimalFormat.getInstance(Locale.getDefault()) as DecimalFormat
    formatter.applyPattern("$currencySymbol$numberPattern")
    return formatter.format(this)
}

fun Double.roundDown(): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this)
}

const val IMAGE_MEDIA_TYPE = "image/jpeg"
const val WEBP_MEDIA_TYPE = "image/webp"