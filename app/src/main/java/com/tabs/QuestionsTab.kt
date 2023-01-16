package com.tabs

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobileBert.TextClassificationHelper
import com.tabs.util.Scores
import org.tensorflow.lite.support.label.Category

@Composable
fun QuestionsTab(context: Context, scores: MutableState<Scores>) {
    var eatingHabits by remember { mutableStateOf("") }
    var sleepHabits by remember { mutableStateOf("") }
    var stressLevels by remember { mutableStateOf("") }
    var alcoholHabits by remember { mutableStateOf("") }

    val textClassificationHelper = TextClassificationHelper(
        context,
        object : TextClassificationHelper.TextResultsListener {

            override fun onError(error: String) {
                println("Error: $error")
            }

            override fun onResult(text: String, results: List<Category>) {
                var positiveScore = 0f
                var negativeScore = 0f
                for (category in results) {
                    if (category.label == "positive") {
                        positiveScore += category.score
                    } else if (category.label == "negative") {
                        negativeScore += category.score
                    }
                }
                when (text) {
                    eatingHabits -> scores.value = scores.value.copy(eatingHabits = Pair(positiveScore,
                        negativeScore))
                    sleepHabits -> scores.value = scores.value.copy(sleepHabits = Pair(positiveScore,
                        negativeScore))
                    stressLevels -> scores.value = scores.value.copy(stressLevels = Pair(positiveScore,
                        negativeScore))
                    alcoholHabits -> scores.value = scores.value.copy(alcoholHabits = Pair(positiveScore,
                        negativeScore))
                    else -> {}
                }

            }
        }
    )



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tell me about today's eating habits")

        TextField(value = eatingHabits, onValueChange = { eatingHabits = it })
        Row {
            Button(onClick = {
                textClassificationHelper.classify(eatingHabits)
                println("Eating Habits positive: ${scores.value.eatingHabits.first}")
                println("Eating Habits negative: ${scores.value.eatingHabits.second}")
            }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                "Positive score: ${scores.value.eatingHabits.first}" +
                        "\nNegative score: ${scores.value.eatingHabits.second}"
            )
        }
        Text("How did you sleep?")
        TextField(value = sleepHabits, onValueChange = { sleepHabits = it })
        Row {
            Button(onClick = {
                textClassificationHelper.classify(sleepHabits)
            }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                "Positive score: ${scores.value.sleepHabits.first}" +
                        "\nNegative score: ${scores.value.sleepHabits.second}"
            )
        }
        Text("How stressed were you today?")
        TextField(value = stressLevels, onValueChange = { stressLevels = it })
        Row {
            Button(onClick = {
                textClassificationHelper.classify(stressLevels)
                //log out the scores
                println("Eating habits: ${scores.value.eatingHabits}")
                println("Sleep habits: ${scores.value.sleepHabits}")
            }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                "Positive score: ${scores.value.stressLevels.first}" +
                        "\nNegative score: ${scores.value.stressLevels.second}"
            )
        }
        Text("How much alcohol did you drink today?")
        TextField(value = alcoholHabits, onValueChange = { alcoholHabits = it })
        Row {
            Button(onClick = {
                textClassificationHelper.classify(alcoholHabits)
            }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                "Positive score: ${scores.value.alcoholHabits.first}" +
                        "\nNegative score: ${scores.value.alcoholHabits.second}"
            )
        }

    }
}












