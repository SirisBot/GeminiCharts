package com.example.myfirstgeminiapp.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DoubleBarChartScreen() {
    val data1 = listOf(4f, 8f, 6f, 12f, 10f)
    val data2 = listOf(3f, 9f, 5f, 7f, 11f)
    val labels = listOf("A", "B", "C", "D", "E")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        DoubleBarGraph(
            data1 = data1,
            data2 = data2,
            maxY = 15f,
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
                .padding(16.dp),
            labels = labels
        )
    }
}

@Composable
fun BarChartScreen() {
    val data1 = listOf(4f, 8f, 6f)
    val labels = listOf("A", "B", "C")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        BarGraph(
            data1 = data1,
            maxY = 15f,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            labels = labels
        )
    }
}

@Composable
fun DoubleBarGraph(
    data1: List<Float>,
    data2: List<Float>,
    labels: List<String>,
    maxY: Float,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val barWidth = size.width / (data1.size * 2 + 1)
        val maxHeight = size.height

        // Draw horizontal grid lines
        for (i in 0..10) {
            val y = maxHeight * i / 10
            drawLine(
                color = Color.Gray,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1f
            )
        }

        data1.forEachIndexed { index, _ ->
            val barHeight1 = data1[index] / maxY * maxHeight
            val x1 = barWidth * (index * 2 + 1)
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x1, maxHeight - barHeight1),
                size = Size(30f, barHeight1)
            )

            val barHeight2 = data2[index] / maxY * maxHeight
            drawRect(
                color = Color.Red,
                topLeft = Offset(x1 + 30f, maxHeight - barHeight2),
                size = Size(30f, barHeight2)
            )
        }

        // Draw X-axis labels
        data1.forEachIndexed { index, _ ->
            val x = barWidth * (index * 2 + 1.5f)
            drawContext.canvas.nativeCanvas.drawText(
                labels[index],
                x,
                maxHeight + 40f,
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = 30f
                    color = android.graphics.Color.BLACK
                }
            )
        }

        // Draw Y-axis on the right
        // Draw Y-axis labels
        for (i in 0..10) {
            val y = maxHeight * i / 10
            val label = (maxY * i / 10).toString()
            drawContext.canvas.nativeCanvas.drawText(
                label,
                size.width + 40f,
                maxHeight - y,
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.LEFT
                    textSize = 30f
                    color = android.graphics.Color.BLACK
                }
            )
        }
    }
}

@Composable
fun BarGraph(
    data1: List<Float>,
    labels: List<String>,
    maxY: Float,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val barWidth = size.width / (data1.size * 2 + 1)
        val maxHeight = size.height

        // Draw horizontal grid lines
        for (i in 0..10) {
            val y = maxHeight * i / 10
            drawLine(
                color = Color.Gray,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1f
            )
        }

        // Draw the bars
        data1.forEachIndexed { index, value ->
            val barHeight = value / maxY * maxHeight
            drawRect(
                color = Color.Blue,
                topLeft = Offset(barWidth * (index * 2 + 1), maxHeight - barHeight),
                size = Size(130f, barHeight)
            )
        }

        // Draw X-axis labels
        data1.forEachIndexed { index, _ ->
            val x = barWidth * (index * 2 + 1.5f)
            drawContext.canvas.nativeCanvas.drawText(
                labels[index],
                x,
                maxHeight + 40f,
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = 30f
                    color = android.graphics.Color.BLACK
                }
            )
        }

        // Draw Y-axis on the right
        // Draw Y-axis labels
        for (i in 0..10) {
            val y = maxHeight * i / 10
            val label = (maxY * i / 10).toString()
            drawContext.canvas.nativeCanvas.drawText(
                label,
                size.width + 40f,
                maxHeight - y,
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.LEFT
                    textSize = 30f
                    color = android.graphics.Color.BLACK
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DoubleBarChartScreenPreview() {
    DoubleBarChartScreen()
}

@Preview(showBackground = true)
@Composable
fun BarChartScreenPreview() {
    BarChartScreen()
}