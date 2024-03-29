package com.ndmine.model

/**
 * A component for the composite pattern. A "dimension" may be 1D (a cell) or multiple dimensions (a row). Rows contain other dimensions of a lower dimension depth.
 */
abstract class Dimension(private val dimensions: List<Int>, private val coords: List<Int>) {

    /**
     * Get the depth of the dimension.
     */
    fun getDepth(): Int {
        return coords.size
    }

    /**
     * Get the number of dimensions
     */
    fun getNumberOfDimensions(): Int {
        return dimensions.size
    }

    /**
     * Get the coordinates as an array.
     */
    fun getCoords(): ArrayList<Int> {
        return ArrayList(coords)
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
     * Get the size of a dimension at a particular index
     */
    fun getDimensionSize(index: Int): Int {
        require(index in dimensions.indices) { "Index was $index, when it should be between 0 and ${dimensions.size}." }

        return dimensions[index]
    }

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