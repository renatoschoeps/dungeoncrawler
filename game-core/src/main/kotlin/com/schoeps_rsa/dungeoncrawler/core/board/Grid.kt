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

        // TODO (próxima fase): substituir por sala carregada de um arquivo
        // JSON de missão. Por ora, uma sala fixa só para vermos o tabuleiro.
        fun sampleRoom(width: Int = 10, height: Int = 7): Grid {
            val cells = buildMap {
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        val pos = Position(x, y)
                        val isBorder = x == 0 || y == 0 || x == width - 1 || y == height - 1
                        val type = if (isBorder) CellType.WALL else CellType.ROOM
                        put(
                            pos,
                            Cell(
                                position = pos,
                                type = type,
                                roomId = if (!isBorder) "room-1" else null,
                                blocked = isBorder
                            )
                        )
                    }
                }
            }
            return Grid(width, height, cells)
        }
    }
}
