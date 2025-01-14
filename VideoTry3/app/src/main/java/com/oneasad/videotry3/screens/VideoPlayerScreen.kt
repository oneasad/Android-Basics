package com.oneasad.videotry3.screens

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.oneasad.videotry3.R
import io.sanghun.compose.video.RepeatMode
import io.sanghun.compose.video.VideoPlayer
import io.sanghun.compose.video.controller.VideoPlayerControllerConfig
import io.sanghun.compose.video.uri.VideoPlayerMediaItem

class VideoPlayerScreen(private val videoUri: Uri): Screen {
    @Composable
    override fun Content() {
        VideoPlayer(
            mediaItems = listOf(

                VideoPlayerMediaItem.StorageMediaItem(
                    storageUri = videoUri
                ),
            ),
            handleLifecycle = true,
            autoPlay = true,
            usePlayerController = true,
            handleAudioFocus = true,
            controllerConfig = VideoPlayerControllerConfig(
                showSpeedAndPitchOverlay = false,
                showSubtitleButton = false,
                showCurrentTimeAndTotalTime = true,
                showBufferingProgress = true,
                showForwardIncrementButton = true,
                showBackwardIncrementButton = true,
                showBackTrackButton = true,
                showNextTrackButton = true,
                showRepeatModeButton = true,
                controllerShowTimeMilliSeconds = 5_000,
                controllerAutoShow = true,
                showFullScreenButton = true
            ),
            volume = 0.5f,  // volume 0.0f to 1.0f
            repeatMode = RepeatMode.ONE,       // or RepeatMode.ALL, RepeatMode.ONE
            modifier = Modifier
                .fillMaxSize()
        )
    }

}