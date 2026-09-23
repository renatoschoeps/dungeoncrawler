package com.jumperfour.dungeoncrawler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jumperfour.dungeoncrawler.core.entity.EntityId
import com.jumperfour.dungeoncrawler.ui.BoardView
import com.jumperfour.dungeoncrawler.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                EngineDebugScreen()
            }
        }
    }
}

@Composable
fun EngineDebugScreen(viewModel: GameViewModel = remember { GameViewModel() }) {
    val state = viewModel.state
    val currentEntity = state.currentEntity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dungeon Crawler — M2 (tabuleiro)", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        BoardView(state = state, modifier = Modifier.padding(horizontal = 8.dp))

        Spacer(Modifier.height(16.dp))
        Text("Rodada: ${state.round}")
        Text("Fase: ${state.phase}")

        val heroName = when (currentEntity) {
            is EntityId.Hero -> state.heroes[currentEntity.id]?.name ?: "?"
            else -> "—"
        }
        Text("Turno atual: $heroName")

        Spacer(Modifier.height(24.dp))
        Button(onClick = { viewModel.endCurrentTurn() }) {
            Text("Encerrar turno")
        }

        viewModel.lastError?.let {
            Spacer(Modifier.height(16.dp))
            Text("Erro: $it", color = MaterialTheme.colorScheme.error)
        }
    }
}
