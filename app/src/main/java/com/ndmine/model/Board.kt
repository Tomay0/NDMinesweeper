package com.ndmine.model

import java.util.*
import java.util.Collections.shuffle
import kotlin.collections.ArrayList

/**
 * Represents the entire collection of cells, with a number of actions you can perform on it
 */
class Board(dimensions: List<Int>) : Row(dimensions, ArrayList()) {

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
        require(coords.size == getNumberOfDimensions()) { "Invalid number of dimensions to the coordinate. Should be ${getNumberOfDimensions()}, but found ${coords.size}" }

        val dimension = getDimension(coords)

        check(dimension is Cell) { "Did not find a cell at the given coordinates." }

        return dimension
    }


    /**
     * Get a set of all adjacent cells from this cell
     */
    fun getAdjacentCells(coords: List<Int>): Set<Cell> {
        val cell = getCell(coords)
        val adjacent: HashSet<Cell> = hashSetOf(cell)

        for (depth in 0 until getNumberOfDimensions()) {
            for (adj in HashSet(adjacent)) {
                var coord = adj.getCoords()

                coord[depth]++

                if (isValidCoord(coord)) {
                    adjacent.add(getCell(coord))
                }

                coord[depth] -= 2
                if (isValidCoord(coord)) {
                    adjacent.add(getCell(coord))
                }
            }

        }

        adjacent.remove(cell)

        return adjacent
    }

    /**
     * Check if the given coordinates are inbounds
     */
    private fun isValidCoord(coords: List<Int>): Boolean {
        require(coords.size == getNumberOfDimensions()) { "Invalid number of dimensions to the coordinate. Should be ${getNumberOfDimensions()}, but found ${coords.size}" }

        for (depth in 0 until getNumberOfDimensions()) {
            if (coords[depth] < 0 || coords[depth] >= getDimensionSize(depth)) {
                return false
            }
        }


        return true
    }

    /**
     * Return the number of mines adjacent to this cell
     */
    fun countAdjacentMines(coords: List<Int>): Int {
        val adjacent = getAdjacentCells(coords)

        var mines = 0

        for (adj in adjacent) {
            if (adj.hasMine()) {
                mines++
            }
        }

        return mines
    }

    /**
     * Expose a cell
     */
    fun exposeCell(coords: List<Int>) {
        val cell = getCell(coords)

        // ignore if already exposed or marked
        if (cell.isExposed() || cell.isMarked()) {
            return
        }

        cell.setExposed(true)

        // if there is a mine at this position, expose everything (You lost)
        if (cell.hasMine()) {
            for(otherCell in allCells) {
                otherCell.setExposed(true)
            }
        }
        // expose all adjacent cells if this cell has no neighbouring mines
        else if(countAdjacentMines(coords) == 0) {
            for(otherCell in getAdjacentCells(coords)) {
                exposeCell(otherCell.getCoords())
            }
        }
    }

    /**
     * Mark a cell
     */
    fun markCell(coords: List<Int>) {

        val cell = getCell(coords)

        cell.setMarked(true)

    }

    /**
     * Un-mark a cell
     */
    fun unmarkCell(coords: List<Int>) {

        val cell = getCell(coords)

        cell.setMarked(false)
    }


    /**
     * Get a list of all cells
     */
    fun getCells(): ArrayList<Cell> {
        return ArrayList(allCells)
    }
}