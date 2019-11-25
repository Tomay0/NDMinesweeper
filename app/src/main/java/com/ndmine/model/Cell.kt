package com.ndmine.model

import java.lang.UnsupportedOperationException

class Cell(coords: List<Int>) : Dimension(ArrayList(), coords) {


    override fun getDimension(index: Int): Dimension {
        throw UnsupportedOperationException("Cell does not have any children.")
    }
}