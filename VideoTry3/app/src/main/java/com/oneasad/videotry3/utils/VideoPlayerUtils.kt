package com.oneasad.videotry3

import android.annotation.SuppressLint
import kotlin.math.log10
import kotlin.math.pow

@SuppressLint("DefaultLocale")
fun Long.toFormattedSize(): String {
    if (this <= 0) return "0 B"

    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (log10(this.toDouble()) / log10(1024.0)).toInt()
    return String.format(
        "%.2f %s",
        this / 1024.0.pow(digitGroups.toDouble()),
        units[digitGroups]
    )
}

@SuppressLint("DefaultLocale")
fun Long.toFormattedTime(): String {
    val hours = this / (1000 * 60 * 60)
    val minutes = (this % (1000 * 60 * 60)) / (1000 * 60)
    val seconds = (this % (1000 * 60)) / 1000

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}