package com.oneasad.videotry3

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.oneasad.videotry3.model.VideoFile
import com.oneasad.videotry3.repo.VideoFilesRepo
import com.oneasad.videotry3.screens.VideoListScreen
import com.oneasad.videotry3.ui.theme.VideoTry3Theme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isGranted = remember { mutableStateOf(false) }

            //<editor-fold desc="temp code for testing">
//            val context = this
//            val videoFilesRepo = VideoFilesRepo(context)
//            val videoFiles: MutableList<VideoFile> = mutableListOf()
//            GlobalScope.launch {
//                videoFilesRepo.getVideoFiles().collect{
//                    videoFiles.add(it)
//                }
//            }
            //</editor-fold>

            VideoTry3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ){
                        val permission = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                            android.Manifest.permission.READ_MEDIA_VIDEO
                        } else {
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        }
                        isGranted.value = checkPermissions(permission)
                        if(isGranted.value) {
                            Navigator(screen = VideoListScreen())
//                            TestScreen(videoFiles)
                        } else {
                            Button(
                                modifier = Modifier.align(Alignment.Center),
                                onClick = {
                                    askPermissions(
                                        permission,
                                        onPermissionGranted = { isGranted.value = true },
                                        onPermissionDenied = {
                                            isGranted.value = false
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Please Grant Permission",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                                             },
                                        onPermissionsResultCallback = {}
                                    )
                                }
                            ) {
                                Text(text = "Allow Permission")
                            }
                    }
                    }
                }
            }
        }
    }
}
