package com.jumperfour.dungeoncrawler.core.entity

data class Monster(
    val id: MonsterId,
    val typeId: String,
    val name: String,
    val movement: Int,
    val attackDice: Int,
    val defenseDice: Int,
    val maxBodyPoints: Int,
    val currentBodyPoints: Int,
    val position: Position,
    val abilityIds: List<String> = emptyList()
) {
    val isDefeated: Boolean get() = currentBodyPoints <= 0
}
