package com.ndmine.model

import java.util.*
import java.util.Collections.shuffle
import kotlin.collections.ArrayList

class Board(private val dimensions: List<Int>) : Row(dimensions, ArrayList()) {

    private val allCells = ArrayList<Cell>()

    /**
     * Initialize by creating a list of all the cells
     */
    init {
        val dimensionQueue = ArrayDeque<Dimension>()

        dimensionQueue.offer(this)

        while (!dimensionQueue.isEmpty()) {
            val fringe = dimensionQueue.poll()

            if (fringe is Cell) {
                allCells.add(fringe)
            } else if (fringe is Row) {
                for (child in fringe.getDimensions()) {
                    dimensionQueue.offer(child)
                }
            }
        }
    }

    /**
     * Get all the dimension sizes
     */
    fun getDimensionSizes(): List<Int> {
        return dimensions
    }

    /**
     * Get the total number of cells
     */
    fun getTotalCells(): Int {
        return allCells.size
    }

    /**
     * Pick num random locations and set each of them to mines
     */
    fun setRandomMines(num: Int) {
        require(num < getTotalCells()) { "Too many mines to add. Maximum: ${getTotalCells()}" }

        // get a list of all the cells
        val cellList = getCells()
        shuffle(cellList)

        for (i in 0 until num) {
            cellList[i].setHasMine(true)
        }

    }


    /**
     * Get a cell at the given coordinates
     */
    fun getCell(coords: List<Int>): Cell {
        require(coords.size == dimensions.size) { "Invalid number of dimensions to the coordinate. Should be ${dimensions.size}, but found ${coords.size}" }

        val dimension = getDimension(coords)

        check(dimension is Cell) { "Did not find a cell at the given coordinates." }

        return dimension
    }

    /**
     * Get all adjacent cells to a cell
     */
    fun getAdjacentCells(cell: Cell): ArrayList<Cell> {
        val coords = cell.getCoords()


    }


    /**
     * Get a list of all cells
     */
    fun getCells(): ArrayList<Cell> {
        return ArrayList(allCells)
    }
}