package com.oneasad.videotry3.viewmodels

import cafe.adriel.voyager.core.model.ScreenModel
import com.oneasad.videotry3.model.VideoFile
import com.oneasad.videotry3.repo.VideoFilesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoViewModel(private val repo: VideoFilesRepo): ScreenModel {
    val state = MutableStateFlow<MutableList<VideoFile>>(mutableListOf())
    private var job: Job? = null

    fun getAllVideoFiles(){
        job?.cancel()
        job = CoroutineScope(Dispatchers.IO).launch {
            val newList = mutableListOf<VideoFile>()
            repo.getVideoFiles().collect{
                newList.add(it)
                state.update {
                    newList
                }
            }
        }
    }
}
