package com.oneasad.videotry3.model

import android.net.Uri

data class VideoFile(
    val name: String,
    val duration: Long,
    val pathUri: Uri,
    val size: Long
)
