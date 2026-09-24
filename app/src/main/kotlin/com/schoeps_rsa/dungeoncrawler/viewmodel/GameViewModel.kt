package com.jumperfour.dungeoncrawler.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jumperfour.dungeoncrawler.core.command.GameCommand
import com.jumperfour.dungeoncrawler.core.engine.GameEngine
import com.jumperfour.dungeoncrawler.core.engine.GameState
import com.jumperfour.dungeoncrawler.core.entity.Hero
import com.jumperfour.dungeoncrawler.core.entity.HeroClass
import com.jumperfour.dungeoncrawler.core.entity.HeroId
import com.jumperfour.dungeoncrawler.core.entity.Position

class GameViewModel {

    private val engine = GameEngine()

    var state: GameState by mutableStateOf(createPlaceholderGame())
        private set

    var lastError: String? by mutableStateOf(null)
        private set

    fun endCurrentTurn() {
        val current = state.currentEntity ?: return
        runCatching {
            engine.execute(state, GameCommand.EndTurn(current))
        }.onSuccess { result ->
            state = result.state
            lastError = null
        }.onFailure { error ->
            lastError = error.message
        }
    }

    private fun createPlaceholderGame(): GameState {
        val heroes = listOf(
            placeholderHero("hero-1", "Herói 1", HeroClass.WARRIOR),
            placeholderHero("hero-2", "Herói 2", HeroClass.MAGE)
        )
        return GameState.sampleQuest(heroes)
    }

    private fun placeholderHero(id: String, name: String, heroClass: HeroClass) = Hero(
        id = HeroId(id),
        name = name,
        heroClass = heroClass,
        movementDice = 2,
        attackDice = 3,
        defenseDice = 2,
        maxBodyPoints = 8,
        maxMindPoints = 4,
        currentBodyPoints = 8,
        currentMindPoints = 4,
        position = Position(0, 0)
    )
}
