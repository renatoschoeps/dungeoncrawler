package com.jumperfour.dungeoncrawler.core.entity

/**
 * Arquétipo do herói. O conjunto inicial é genérico e substituível
 * por conteúdo próprio/licenciado.
 */
enum class HeroClass {
    WARRIOR,
    DWARF,
    RANGER,
    MAGE
}

data class Hero(
    val id: HeroId,
    val name: String,
    val heroClass: HeroClass,
    val movementDice: Int,
    val attackDice: Int,
    val defenseDice: Int,
    val maxBodyPoints: Int,
    val maxMindPoints: Int,
    val currentBodyPoints: Int,
    val currentMindPoints: Int,
    val position: Position,
    val inventory: List<ItemId> = emptyList(),
    val equippedItems: List<ItemId> = emptyList(),
    val spells: List<SpellId> = emptyList(),
    val statusEffects: List<StatusEffect> = emptyList()
) {
    val isDefeated: Boolean get() = currentBodyPoints <= 0
}

data class StatusEffect(
    val id: String,
    val remainingRounds: Int
)
