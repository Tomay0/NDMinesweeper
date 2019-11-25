package com.ndmine.model

open class Row(dimensions: List<Int>, coords: List<Int>) : Dimension(dimensions, coords) {
    private val cols: ArrayList<Dimension> = ArrayList()

    init {
        require(dimensions.isNotEmpty()) { "Child dimensions must be listed. Consider using Cell instead." }

        // sublist of the list of dimension sizes
        val childDimensions : List<Int> = if(dimensions.size == 1) {
            ArrayList()
        }
        else {
            dimensions.takeLast(dimensions.size - 1)
        }

        // initialize the cols
        for (i in 0 until dimensions[0]) {
            val childCoords: ArrayList<Int> = ArrayList()
            childCoords.addAll(coords)
            childCoords.add(i)

            if(childDimensions.isNotEmpty()) {
                cols.add(Row(childDimensions, childCoords))
            }
            else {
                cols.add(Cell(childCoords))
            }
        }
    }

    override fun getDimension(index: Int): Dimension {
        require(index in cols.indices) { "$index not a valid index within cols array." }

        return cols[index]
    }
}