package com.jumperfour.dungeoncrawler.core.command

import com.jumperfour.dungeoncrawler.core.entity.*

sealed interface GameCommand {
    data class Move(val heroId: HeroId, val to: Position) : GameCommand
    data class Attack(val attacker: EntityId, val target: EntityId) : GameCommand
    data class OpenDoor(val heroId: HeroId, val doorId: DoorId) : GameCommand
    data class Search(val heroId: HeroId, val type: SearchType) : GameCommand
    data class CastSpell(val casterId: HeroId, val spellId: SpellId, val targetId: EntityId?) : GameCommand
    data class EndTurn(val entity: EntityId) : GameCommand
}

enum class SearchType { TREASURE, TRAPS, SECRET_DOORS }
