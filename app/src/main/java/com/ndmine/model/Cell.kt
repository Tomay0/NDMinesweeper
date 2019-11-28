package com.ndmine.model

import java.lang.UnsupportedOperationException

/**
 * Represents a single cell on a board
 */
class Cell(coords: List<Int>) : Dimension(ArrayList(), coords) {
    private var hasMine = false
    private var isExposed = false
    private var isMarked = false

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
    fun hasMine() : Boolean {
        return hasMine
    }

    /**
     * Set the cell as marked/unmarked
     */
    fun setMarked(marked: Boolean) {
        this.isMarked = marked
    }

    /**
     * Set the cell as exposed/unexposed
     */
    fun setExposed(exposed: Boolean) {
        this.isExposed = exposed
    }

    /**
     * Returns if this cell is exposed
     */
    fun isExposed(): Boolean {
        return isExposed
    }

    /**
     * Returns if this cell is marked
     */
    fun isMarked(): Boolean {
        return isMarked
    }
}