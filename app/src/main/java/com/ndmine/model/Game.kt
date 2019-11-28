package com.ndmine.model

import java.lang.Math.round
import kotlin.math.roundToInt

/**
 * The game class helps generate boards and handles game states
 */
class Game {
    private var numDimensions = 2
    private var boardSize = 8
    private var mineRatio = 0.05

    private var board: Board = Board(listOf(0))


    fun generateBoard() {

        val dimensions = ArrayList<Int>()
        for (i in 1..numDimensions) {
            dimensions.add(boardSize)
        }

        board = Board(dimensions)

        val numMines = (mineRatio * board.getTotalCells()).roundToInt()

        board.setRandomMines(numMines)
    }


}