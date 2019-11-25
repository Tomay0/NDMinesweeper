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
        // create 3d 5x5x5 board
        val board = Board(listOf(3, 4, 6, 2))

        // assert all coordinates are valid
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

}