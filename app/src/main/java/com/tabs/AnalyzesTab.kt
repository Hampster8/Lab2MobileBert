package com.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tabs.util.Scores


@Composable
fun AnalyzesTab(scores: MutableState<Scores>) {


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp)
        .verticalScroll(rememberScrollState())) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text("Eating Habits")
                PieChart(
                    values = listOf(
                        scores.value.eatingHabits.first,
                        scores.value.eatingHabits.second
                    ),
                    legend = listOf("Positive", "Negative")
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Sleep Habits")
                PieChart(
                    values = listOf(
                        scores.value.sleepHabits.first,
                        scores.value.sleepHabits.second
                    ),
                    legend = listOf("Positive", "Negative")
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Stress Levels")
                PieChart(
                    values = listOf(
                        scores.value.stressLevels.first,
                        scores.value.stressLevels.second
                    ),
                    legend = listOf("Positive", "Negative")
                )
            }

            //Spacer(modifier = Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Alcohol Habits")
                PieChart(
                    values = listOf(
                        scores.value.alcoholHabits.first,
                        scores.value.alcoholHabits.second
                    ),
                    legend = listOf("Positive", "Negative")
                )
            }
        }
        Text(getDietText(scores))
        Text(getSleepText(scores))
        Text(getStressText(scores))
        Text(getAlcoholText(scores))

    }



}

fun getDietText(scores: MutableState<Scores>): String {
    val dietPositive =
        "Today, you had a healthy diet, including a balance of necessary nutrients and adequate hydration."
    val dietNegative =
        "Today, you had an unhealthy diet, lacking in necessary nutrients and not getting adequate hydration."
    return if (scores.value.eatingHabits.first > scores.value.eatingHabits.second) {
        dietPositive
    } else {
        dietNegative
    }
}

fun getSleepText(scores: MutableState<Scores>): String {
    val sleepPositive =
        "Today, you had a full night of restful sleep, and woke up feeling refreshed and energized."
    val sleepNegative =
        "Today, you had a poor night of sleep, and woke up feeling tired and groggy."
    return if (scores.value.sleepHabits.first > scores.value.sleepHabits.second) {
        sleepPositive
    } else {
        sleepNegative
    }
}

fun getStressText(scores: MutableState<Scores>): String {
    val stressPositive =
        "Today, you felt calm and relaxed, and was able to manage any stressors that came their way."
    val stressNegative =
        "Today, you felt anxious and overwhelmed, unable to manage stressors effectively."
    return if (scores.value.stressLevels.first > scores.value.stressLevels.second) {
        stressPositive
    } else {
        stressNegative
    }
}

fun getAlcoholText(scores: MutableState<Scores>): String {
    val alcoholPositive =
        "Today, you made the conscious decision to not consume any alcohol, which is beneficial for their overall health and well-being."
    val alcoholNegative =
        "Today, you made the decision to consume a significant amount of alcohol, which negatively impacted their overall health and well-being."
    return if (scores.value.alcoholHabits.first > scores.value.alcoholHabits.second) {
        alcoholPositive
    } else {
        alcoholNegative
    }
}



@Composable
fun PieChart(
    values: List<Float?>,
    colors: List<Color> = listOf(Color(0xFF388E3C), Color(0xFFC2185B)),
    legend: List<String>,
    size: Dp = 175.dp,
) {
    val floatValues = values.filterNotNull()
    // Sum of all the values
    val sumOfValues = floatValues.sum()
    // Calculate each proportion value
    val proportions = floatValues.map {
        (it * 100) / sumOfValues
    }
    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        (360 * it) / 100
    }
    Canvas(modifier = Modifier.size(size = size)) {
        var startAngle = -90f
        for (i in sweepAngles.indices) {
            drawArc(
                color = colors[i],
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = true
            )
            startAngle += sweepAngles[i]
        }
    }
    Spacer(modifier = Modifier.height(32.dp))
    Column {
        for (i in values.indices) {
            DisplayLegend(color = colors[i], legend = legend[i])
        }
    }
}


@Composable
fun DisplayLegend(color: Color, legend: String) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.width(16.dp),
            thickness = 4.dp,
            color = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend,
            color = Color.Black
        )
    }
}

