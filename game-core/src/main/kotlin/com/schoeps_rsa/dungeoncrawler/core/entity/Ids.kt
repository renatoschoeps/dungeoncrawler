package com.jumperfour.dungeoncrawler.core.entity

/** Coordenada de uma célula do grid. Imutável. */
data class Position(val x: Int, val y: Int) {
    fun isAdjacentTo(other: Position): Boolean {
        val dx = kotlin.math.abs(x - other.x)
        val dy = kotlin.math.abs(y - other.y)
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1)
    }
}

/**
 * IDs tipados: evita trocar por engano um HeroId por um MonsterId
 * em tempo de compilação (bug comum quando tudo é String).
 */
@JvmInline
value class HeroId(val value: String)

@JvmInline
value class MonsterId(val value: String)

@JvmInline
value class DoorId(val value: String)

@JvmInline
value class TrapId(val value: String)

@JvmInline
value class ItemId(val value: String)

@JvmInline
value class SpellId(val value: String)

@JvmInline
value class ObjectiveId(val value: String)

/** Identifica qualquer entidade posicionável no tabuleiro (herói ou monstro). */
sealed interface EntityId {
    data class Hero(val id: HeroId) : EntityId
    data class Monster(val id: MonsterId) : EntityId
}
