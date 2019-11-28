package com.ndmine

import com.ndmine.model.Board
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Test the minesweeper multi-dimensional model
 */
class ModelTest {

    @Test
    fun testCreateBoard() {
        // create 3d 5x5x5 board
        val board = Board(listOf(5, 5, 5))

        // assert all coordinates are valid
        for (x in 0..4) {
            for (y in 0..4) {
                for (z in 0..4) {
                    val cell = board.getCell(listOf(x, y, z))

                    assertEquals(x, cell.getCoord(0))
                    assertEquals(y, cell.getCoord(1))
                    assertEquals(z, cell.getCoord(2))

                    assertEquals(3, cell.getDepth())
                }
            }
        }
    }

    @Test
    fun testCreate4DBoard() {
        val board = Board(listOf(3, 4, 6, 2))

        for (x in 0..2) {
            for (y in 0..3) {
                for (z in 0..5) {
                    for (w in 0..1) {
                        val cell = board.getCell(listOf(x, y, z, w))

                        assertEquals(x, cell.getCoord(0))
                        assertEquals(y, cell.getCoord(1))
                        assertEquals(z, cell.getCoord(2))
                        assertEquals(w, cell.getCoord(3))

                        assertEquals(4, cell.getDepth())
                    }
                }
            }
        }
    }

    @Test
    fun testGetTotalCells() {
        val board = Board(listOf(3, 3, 3))

        assertEquals(27, board.getTotalCells())
    }

    @Test
    fun testCreateMines() {
        val board = Board(listOf(5, 5, 5))

        board.setRandomMines(30)

        var mines = 0

        for (x in 0..4) {
            for (y in 0..4) {
                for (z in 0..4) {
                    val cell = board.getCell(listOf(x, y, z))

                    if (cell.getHasMine()) {
                        mines++
                    }
                }
            }
        }

        assertEquals(30, mines)
    }

    @Test
    fun testGetAdjacent2D() {
        val board = Board(listOf(10, 10))

        // test the number of adjacent cells is correct
        assertEquals(8, board.getAdjacentCells(board.getCell(listOf(2, 2))).size)
        assertEquals(3, board.getAdjacentCells(board.getCell(listOf(0, 0))).size)
        assertEquals(5, board.getAdjacentCells(board.getCell(listOf(0, 1))).size)
        assertEquals(3, board.getAdjacentCells(board.getCell(listOf(9, 9))).size)

        // test the locations
        val adjacent = HashSet(board.getAdjacentCells(board.getCell(listOf(3, 3))))
        adjacent.remove(board.getCell(listOf(2, 2)))
        adjacent.remove(board.getCell(listOf(2, 3)))
        adjacent.remove(board.getCell(listOf(2, 4)))
        adjacent.remove(board.getCell(listOf(4, 2)))
        adjacent.remove(board.getCell(listOf(4, 3)))
        adjacent.remove(board.getCell(listOf(4, 4)))
        adjacent.remove(board.getCell(listOf(3, 2)))
        adjacent.remove(board.getCell(listOf(3, 4)))

        assertEquals(0, adjacent.size)


    }

    @Test
    fun testGetAdjacent4D() {
        val board = Board(listOf(5, 5, 5, 5))


        assertEquals(80, board.getAdjacentCells(board.getCell(listOf(3, 3, 3, 3))).size)
    }
}