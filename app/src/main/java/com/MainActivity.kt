package com

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ui.theme.Lab2MobileBertTheme
import com.tabs.util.Scores
import com.viewpager.PagerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2MobileBertTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    MyApp(context = this)
                }
            }
        }
    }
}

@Composable
fun MyApp(context: Context) {
    val scores = remember {
        mutableStateOf(Scores(
            eatingHabits = Pair(0f, 0f),
            sleepHabits = Pair(0f, 0f),
            stressLevels = Pair(0f, 0f),
            alcoholHabits = Pair(0f, 0f)))
    }
    PagerScreen(context, scores = scores)
}





