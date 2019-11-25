package com.ndmine.model

import java.lang.IllegalStateException

class Board(private val dimensions: List<Int>) : Row(dimensions, ArrayList()) {

    /**
     * Get all the dimensions
     */
    fun getDimensions(): List<Int> {
        return dimensions
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
}