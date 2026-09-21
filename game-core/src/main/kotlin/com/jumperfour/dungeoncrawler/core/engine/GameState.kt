package com.jumperfour.dungeoncrawler.core.engine

import com.jumperfour.dungeoncrawler.core.board.Door
import com.jumperfour.dungeoncrawler.core.board.Grid
import com.jumperfour.dungeoncrawler.core.board.Trap
import com.jumperfour.dungeoncrawler.core.entity.*

enum class TurnPhase { HERO_TURN, ENEMY_TURN }

data class GameLogEntry(val round: Int, val message: String)

data class GameState(
    val questId: String,
    val round: Int,
    val grid: Grid,
    val heroes: Map<HeroId, Hero>,
    val monsters: Map<MonsterId, Monster>,
    val doors: Map<DoorId, Door>,
    val traps: Map<TrapId, Trap>,
    val discoveredCells: Set<Position>,
    val turnOrder: List<EntityId>,
    val currentTurnIndex: Int,
    val phase: TurnPhase,
    val eventLog: List<GameLogEntry> = emptyList(),
    val completedTriggerIds: Set<String> = emptySet(),
    val objectivesStatus: Map<ObjectiveId, Boolean> = emptyMap()
) {
    val currentEntity: EntityId? get() = turnOrder.getOrNull(currentTurnIndex)

    companion object {
        fun newGame(questId: String, heroes: List<Hero>): GameState {
            val turnOrder = heroes.map { EntityId.Hero(it.id) }
            return GameState(
                questId = questId,
                round = 1,
                grid = Grid.empty(width = 1, height = 1),
                heroes = heroes.associateBy { it.id },
                monsters = emptyMap(),
                doors = emptyMap(),
                traps = emptyMap(),
                discoveredCells = emptySet(),
                turnOrder = turnOrder,
                currentTurnIndex = 0,
                phase = TurnPhase.HERO_TURN
            )
        }
    }
}
