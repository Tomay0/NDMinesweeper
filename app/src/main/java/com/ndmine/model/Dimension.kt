package com.ndmine.model

abstract class Dimension(private val dimensions: List<Int>, private val coords: List<Int>) {

    /**
     * Get the depth of the dimension.
     */
    fun getDepth(): Int {
        return coords.size
    }

    /**
     * Get the coordinates as an array.
     */
    fun getCoords(): List<Int> {
        return coords
    }

    /**
     * Get the coord for a parent dimension.
     */
    fun getCoord(index: Int): Int {
        require(index in coords.indices) { "Index was $index, when it should be between 0 and ${coords.size}." }

        return coords[index]
    }

    /**
     * Get a child dimension at a particular index
     */
    abstract fun getDimension(index: Int): Dimension

    /**
     * Return a dimension for the given coordinates. Found recursively.
     */
    fun getDimension(coords: List<Int>): Dimension {
        require(coords.isNotEmpty()) { "Coords array is empty." }
        return if (coords.size == 1) {
            getDimension(coords[0])
        } else {
            val dimension = getDimension(coords[0])
            val last: List<Int> = coords.takeLast(coords.size - 1)
            dimension.getDimension(last)
        }
    }

}