package com.hgshkt.vrgsofttask.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hgshkt.vrgsofttask.presentation.navigation.NavGraph
import com.hgshkt.vrgsofttask.presentation.ui.theme.VRGSoftTaskTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VRGSoftTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->
                    val controller = rememberNavController()

                    NavGraph(controller = controller)
                }
            }
        }
    }
}