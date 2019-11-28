package com.ndmine.model

import java.lang.UnsupportedOperationException

class Cell(coords: List<Int>) : Dimension(ArrayList(), coords) {
    private var hasMine = false

    override fun getDimension(index: Int): Dimension {
        throw UnsupportedOperationException("Cell does not have any children.")
    }

    /**
     * Set if the cell contains a mine
     */
    fun setHasMine(hasMine : Boolean) {
        this.hasMine = hasMine
    }

    /**
     * Get if the cell contains a mine
     */
    fun getHasMine() : Boolean {
        return hasMine
    }
}