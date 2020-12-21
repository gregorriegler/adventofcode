class Right3Down1Slope(private val wood: Wood) : Slope {
    override fun iterator(): Iterator<Field> {
        return SlopeIterator(wood)
    }
}

class SlopeIterator(private val wood: Wood) : Iterator<Field> {
    var row = 0
    var col = 0

    companion object {
        const val NEXT_ROW = 1
        const val NEXT_COL = 3
    }

    override fun hasNext(): Boolean {
        return wood.at(row + NEXT_ROW, col + NEXT_COL) != null
    }

    override fun next(): Field {
        row += NEXT_ROW
        col += NEXT_COL
        return wood.at(row, col)!!
    }
}
