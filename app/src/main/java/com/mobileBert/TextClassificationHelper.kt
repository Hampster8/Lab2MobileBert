package com.mobileBert

import android.content.Context

import org.tensorflow.lite.support.label.Category
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.text.nlclassifier.BertNLClassifier
import java.util.concurrent.ScheduledThreadPoolExecutor


class TextClassificationHelper(
    private val context: Context,
    private val listener: TextResultsListener,
) {
    private lateinit var bertClassifier: BertNLClassifier
    private lateinit var executor: ScheduledThreadPoolExecutor

    init {
        initClassifier()
    }

    private fun initClassifier() {
        val baseOptionsBuilder = BaseOptions.builder()
        val baseOptions = baseOptionsBuilder.build()

        val options = BertNLClassifier.BertNLClassifierOptions
            .builder()
            .setBaseOptions(baseOptions)
            .build()

        bertClassifier = BertNLClassifier.createFromFileAndOptions(
            context,
            MOBILEBERT,
            options)
    }

    fun classify(text: String) {
        executor = ScheduledThreadPoolExecutor(1)
        executor.execute {
            val results: List<Category> = bertClassifier.classify(text)
            listener.onResult(text, results)
        }
    }

    interface TextResultsListener {
        fun onError(error: String)
        fun onResult(text: String, results: List<Category>)
    }

    companion object {
        const val MOBILEBERT = "mobilebert.tflite"
    }
}