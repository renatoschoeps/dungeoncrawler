package com.jumperfour.dungeoncrawler.core.event

import com.jumperfour.dungeoncrawler.core.entity.DoorId
import com.jumperfour.dungeoncrawler.core.entity.EntityId
import com.jumperfour.dungeoncrawler.core.entity.Position

sealed interface GameEvent {
    data class HeroMoved(val hero: EntityId.Hero, val from: Position, val to: Position) : GameEvent
    data class DoorOpened(val doorId: DoorId) : GameEvent
    data class AttackResolved(
        val attacker: EntityId,
        val target: EntityId,
        val damage: Int
    ) : GameEvent
    data class EntityDefeated(val entity: EntityId) : GameEvent
    data class RoundStarted(val round: Int) : GameEvent
    data class RoundEnded(val round: Int) : GameEvent
    data class TurnEnded(val entity: EntityId) : GameEvent
}
