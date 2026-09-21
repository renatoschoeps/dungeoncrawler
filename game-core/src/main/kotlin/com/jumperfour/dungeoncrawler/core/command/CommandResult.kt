package com.jumperfour.dungeoncrawler.core.command

import com.jumperfour.dungeoncrawler.core.engine.GameState
import com.jumperfour.dungeoncrawler.core.event.GameEvent

sealed interface ValidationResult {
    data object Valid : ValidationResult
    data class Invalid(val reason: String) : ValidationResult
}

data class CommandResult(
    val state: GameState,
    val events: List<GameEvent>
)

interface CommandHandler<C : GameCommand> {
    fun validate(state: GameState, command: C): ValidationResult
    fun apply(state: GameState, command: C): CommandResult
}
