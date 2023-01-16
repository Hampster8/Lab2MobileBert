package com.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Preview
@Composable
fun HomeTab() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp, top = 50.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            val description =
                "Mental health refers to a person's overall psychological well-being. " +
                        "It encompasses a wide range of emotions, behaviors, and thoughts that can " +
                        "affect how a person feels, acts, and thinks. It's important to maintain " +
                        "good mental health in order to lead a happy and fulfilled life."
            Box(modifier = Modifier.size(300.dp, 150.dp)) {
                Text(description, style = MaterialTheme.typography.subtitle1)
            }
            YoutubeCard("https://img.youtube.com/vi/-OAjfrhuwRk/sddefault.jpg",
                "https://www.youtube.com/watch?v=-OAjfrhuwRk&",
                "Introduction")
            YoutubeCard("https://img.youtube.com/vi/98V1q5k8x5E/sddefault.jpg",
                "https://www.youtube.com/watch?v=98V1q5k8x5E&",
                "Sleep")
            YoutubeCard("https://img.youtube.com/vi/xyQY8a-ng6g/sddefault.jpg",
                "https://www.youtube.com/watch?v=xyQY8a-ng6g",
                "Nutrition")
            YoutubeCard("https://img.youtube.com/vi/DxIDKZHW3-E/sddefault.jpg",
                "https://www.youtube.com/watch?v=DxIDKZHW3-E",
                "Stress")
            YoutubeCard("https://img.youtube.com/vi/hzcZd08PqSQ/sddefault.jpg",
                "https://www.youtube.com/watch?v=hzcZd08PqSQ",
                "Alcohol")

        }
    }
}


@Composable
fun YoutubeCard(imageUrl: String, youtubeLink: String, descriptiveText: String) {
    val uriHandler = LocalUriHandler.current
    Card(Modifier
        .fillMaxWidth()
        .padding(16.dp), elevation = 20.dp) {
        Row(Modifier.padding(15.dp)) {
            Column(Modifier.wrapContentSize(Alignment.CenterStart)) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = descriptiveText,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RectangleShape)
                        .clickable(onClick = { uriHandler.openUri(youtubeLink) }))
            }
            Text(descriptiveText, Modifier.align(Alignment.CenterVertically))
        }


    }
}




