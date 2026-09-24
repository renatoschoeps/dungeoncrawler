package com.jumperfour.dungeoncrawler.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.jumperfour.dungeoncrawler.core.board.CellType
import com.jumperfour.dungeoncrawler.core.engine.GameState

private val floorColor = Color(0xFFDCC9A3)
private val wallColor = Color(0xFF3B2E23)
private val heroColors = listOf(Color(0xFFB33A3A), Color(0xFF2E5FA3), Color(0xFF3A8C4E), Color(0xFF9B59B6))

@Composable
fun BoardView(state: GameState, modifier: Modifier = Modifier) {
    val grid = state.grid
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(grid.width.toFloat() / grid.height.toFloat())
    ) {
        val cellWidth = size.width / grid.width
        val cellHeight = size.height / grid.height

        grid.cells.values.forEach { cell ->
            val color = if (cell.type == CellType.WALL) wallColor else floorColor
            drawRect(
                color = color,
                topLeft = Offset(cell.position.x * cellWidth, cell.position.y * cellHeight),
                size = Size(cellWidth, cellHeight)
            )
        }

        state.heroes.values.forEachIndexed { index, hero ->
            val centerX = hero.position.x * cellWidth + cellWidth / 2
            val centerY = hero.position.y * cellHeight + cellHeight / 2
            drawCircle(
                color = heroColors[index % heroColors.size],
                radius = minOf(cellWidth, cellHeight) * 0.35f,
                center = Offset(centerX, centerY)
            )
        }
    }
}
