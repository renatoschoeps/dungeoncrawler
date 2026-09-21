package com.jumperfour.dungeoncrawler.core.board

import com.jumperfour.dungeoncrawler.core.entity.DoorId
import com.jumperfour.dungeoncrawler.core.entity.Position
import com.jumperfour.dungeoncrawler.core.entity.TrapId

enum class DoorOrientation { HORIZONTAL, VERTICAL }
enum class DoorState { CLOSED, OPEN }

data class Door(
    val id: DoorId,
    val position: Position,
    val orientation: DoorOrientation,
    val state: DoorState = DoorState.CLOSED
)

enum class TrapState { HIDDEN, DISCOVERED, TRIGGERED, DISARMED }

data class Trap(
    val id: TrapId,
    val typeId: String,
    val position: Position,
    val state: TrapState = TrapState.HIDDEN
)
