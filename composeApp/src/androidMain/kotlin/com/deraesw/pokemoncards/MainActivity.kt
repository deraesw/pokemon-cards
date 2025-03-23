package com.deraesw.pokemoncards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val corountine = rememberCoroutineScope()
//            SideEffect {
//                println("SideEffect")
//                corountine.launch {
//                    println("launch")
//                    SyncManager.initialSync()
//                }
//            }
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}