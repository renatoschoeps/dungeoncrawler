package com.jumperfour.dungeoncrawler.core.command.handlers

import com.jumperfour.dungeoncrawler.core.command.CommandHandler
import com.jumperfour.dungeoncrawler.core.command.CommandResult
import com.jumperfour.dungeoncrawler.core.command.GameCommand
import com.jumperfour.dungeoncrawler.core.command.ValidationResult
import com.jumperfour.dungeoncrawler.core.engine.GameState
import com.jumperfour.dungeoncrawler.core.engine.TurnPhase
import com.jumperfour.dungeoncrawler.core.event.GameEvent

class EndTurnHandler : CommandHandler<GameCommand.EndTurn> {

    override fun validate(state: GameState, command: GameCommand.EndTurn): ValidationResult {
        if (state.phase != TurnPhase.HERO_TURN) {
            return ValidationResult.Invalid("Não é a fase dos heróis.")
        }
        if (state.currentEntity != command.entity) {
            return ValidationResult.Invalid("Não é o turno desta entidade.")
        }
        return ValidationResult.Valid
    }

    override fun apply(state: GameState, command: GameCommand.EndTurn): CommandResult {
        val events = mutableListOf<GameEvent>(GameEvent.TurnEnded(command.entity))
        val nextIndex = state.currentTurnIndex + 1

        if (nextIndex < state.turnOrder.size) {
            return CommandResult(state.copy(currentTurnIndex = nextIndex), events)
        }

        events.add(GameEvent.RoundEnded(state.round))

        if (state.monsters.isEmpty()) {
            val newRound = state.round + 1
            events.add(GameEvent.RoundStarted(newRound))
            return CommandResult(
                state.copy(round = newRound, currentTurnIndex = 0, phase = TurnPhase.HERO_TURN),
                events
            )
        }

        return CommandResult(state.copy(phase = TurnPhase.ENEMY_TURN), events)
    }
}
