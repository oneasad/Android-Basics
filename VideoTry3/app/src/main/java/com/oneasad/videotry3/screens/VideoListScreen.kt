package com.oneasad.videotry3.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.oneasad.videotry3.model.VideoFile
import com.oneasad.videotry3.repo.VideoFilesRepo
import com.oneasad.videotry3.screens.components.VideoItem
import com.oneasad.videotry3.viewmodels.VideoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class VideoListScreen(private val modifier: Modifier = Modifier): Screen {
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel = rememberScreenModel {
            VideoViewModel(VideoFilesRepo(context))
        }
        val list by viewModel.state.collectAsState()
        viewModel.getAllVideoFiles()
        if(list.isEmpty()){
            Text(
                text = "No Video Files Found",
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxSize()
            )
        } else {
            val navigator = LocalNavigator.currentOrThrow
            LazyColumn (
                modifier = modifier.fillMaxSize()
            ){
                items(list.size){
                    VideoItem(
                        videoFile = list[it],
                        modifier = Modifier.padding(8.dp)
                    ){
                        navigator.push(VideoPlayerScreen(list[it].pathUri))
                    }
                }
            }
        }
    }

}