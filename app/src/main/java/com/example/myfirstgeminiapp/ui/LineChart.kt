package com.example.myfirstgeminiapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AreaChartScreen() {
    val data = listOf(
        Pair(LocalDate.parse("2023-01-01"), 10f),
        Pair(LocalDate.parse("2023-01-02"), 20f),
        Pair(LocalDate.parse("2023-01-03"), 15f),
        Pair(LocalDate.parse("2023-01-04"), 30f),
        Pair(LocalDate.parse("2023-01-05"), 25f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Area Chart", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))
        AreaChart(data)
    }
}

@Composable
fun AreaChart(data: List<Pair<LocalDate, Float>>) {
    val dateFormatter = DateTimeFormatter.ofPattern("MM-dd")
    val maxValue = data.maxOf { it.second }
    val minValue = data.minOf { it.second }
    val step = (maxValue - minValue) / 7  // 8 steps mean 7 intervals
    val adjustedMaxValue = maxValue + 2 * step  // Adjust max value to be 2 steps higher
    val yAxisPadding = 20f  // Padding between the path and y-axis labels

    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        val width = size.width
        val height = size.height
        val spacing = width / data.size  // Adjust spacing to prevent crossing the edge

        val path1 = Path().apply {
            moveTo(0f, height)
            data.forEachIndexed { index, pair ->
                val x = index * spacing
                val y = height - (pair.second - minValue) / (adjustedMaxValue - minValue) * height
                lineTo(x, y)
            }
            lineTo(width, height)
            close()
        }

        drawPath(
            path = path1,
            color = Color.LightGray,
            style = Fill
        )

        // Draw horizontal grid lines and y-axis labels on the right side
        for (i in 0..7) {
            val value = minValue + i * step
            val y = height - (value - minValue) / (adjustedMaxValue - minValue) * height
            drawLine(
                color = Color.Gray,
                start = Offset(0f, y),
                end = Offset(width, y),
                strokeWidth = 1f
            )
            drawContext.canvas.nativeCanvas.drawText(
                value.toString(),
                width + yAxisPadding,  // Add padding to the y-axis labels
                y,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 24f
                }
            )
        }

        // Draw x-axis labels below the chart
        data.forEachIndexed { index, pair ->
            val x = index * spacing
            drawContext.canvas.nativeCanvas.drawText(
                pair.first.format(dateFormatter),
                x,
                height + 40f,  // Move x-axis labels below the chart
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 24f
                }
            )
        }
    }
}