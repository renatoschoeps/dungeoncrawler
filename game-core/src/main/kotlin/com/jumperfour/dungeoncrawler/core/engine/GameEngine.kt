package com.jumperfour.dungeoncrawler.core.engine

import com.jumperfour.dungeoncrawler.core.command.CommandResult
import com.jumperfour.dungeoncrawler.core.command.GameCommand
import com.jumperfour.dungeoncrawler.core.command.ValidationResult
import com.jumperfour.dungeoncrawler.core.command.handlers.EndTurnHandler

class InvalidCommandException(val reason: String) : IllegalStateException(reason)

class GameEngine {

    private val endTurnHandler = EndTurnHandler()

    fun execute(state: GameState, command: GameCommand): CommandResult {
        return when (command) {
            is GameCommand.EndTurn -> dispatch(state, command, endTurnHandler)
            is GameCommand.Move ->
                throw NotImplementedError("Move chega na fase M3 (grid + pathfinding).")
            is GameCommand.Attack ->
                throw NotImplementedError("Attack chega na fase M7 (sistema de dados + combate).")
            is GameCommand.OpenDoor ->
                throw NotImplementedError("OpenDoor chega na fase M6 (portas + fog of war).")
            is GameCommand.Search ->
                throw NotImplementedError("Search chega na fase M9 (armadilhas e buscas).")
            is GameCommand.CastSpell ->
                throw NotImplementedError("CastSpell chega na fase M11 (magias).")
        }
    }

    private fun <C : GameCommand> dispatch(
        state: GameState,
        command: C,
        handler: com.jumperfour.dungeoncrawler.core.command.CommandHandler<C>
    ): CommandResult {
        when (val validation = handler.validate(state, command)) {
            is ValidationResult.Invalid -> throw InvalidCommandException(validation.reason)
            ValidationResult.Valid -> Unit
        }
        return handler.apply(state, command)
    }
}
