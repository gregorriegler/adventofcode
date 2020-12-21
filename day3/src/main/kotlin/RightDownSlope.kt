class RightDownSlope(
    private val wood: Wood,
    private val nextCol: Int,
    private val nextRow: Int
) : Slope {
    override fun iterator(): Iterator<Field> {
        return SlopeIterator(wood, nextCol, nextRow)
    }
}

class SlopeIterator(
    private val wood: Wood,
    private val nextCol: Int,
    private val nextRow: Int
) : Iterator<Field> {
    var row = 0
    var col = 0

    override fun hasNext(): Boolean {
        return wood.at(row + nextRow, col + nextCol) != null
    }

    override fun next(): Field {
        row += nextRow
        col += nextCol
        return wood.at(row, col)!!
    }
}
