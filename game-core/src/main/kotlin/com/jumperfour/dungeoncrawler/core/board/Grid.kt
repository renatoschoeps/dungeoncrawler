package com.jumperfour.dungeoncrawler.core.board

import com.jumperfour.dungeoncrawler.core.entity.Position

enum class CellType { ROOM, CORRIDOR, WALL, VOID }

data class Cell(
    val position: Position,
    val type: CellType,
    val roomId: String? = null,
    val blocked: Boolean = false
)

data class Grid(
    val width: Int,
    val height: Int,
    val cells: Map<Position, Cell>
) {
    fun cellAt(position: Position): Cell? = cells[position]

    fun isWithinBounds(position: Position): Boolean =
        position.x in 0 until width && position.y in 0 until height

    companion object {
        fun empty(width: Int, height: Int): Grid {
            val cells = buildMap {
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        val pos = Position(x, y)
                        put(pos, Cell(position = pos, type = CellType.VOID))
                    }
                }
            }
            return Grid(width, height, cells)
        }
    }
}
