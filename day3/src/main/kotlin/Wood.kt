class Wood(input: String) {

    private val rows: List<Row> = input.lines().map { Row(it) }

    fun at(row: Int, col: Int): Field? {
        if(row >= rows.size) return null
        return rows[row].elementAt(col)
    }
}

class Row(row: String): Sequence<Field> {

    private val cols: List<Field> = row.toCharArray().map(Field.Companion::of)

    override fun iterator(): Iterator<Field> = LoopingIterator(cols)

}

/**
 * not thread-safe, could use result=index%length instead
 */
class LoopingIterator<T>(private val fields: Iterable<T>) : Iterator<T> {
    private var iterator = fields.iterator()

    override fun hasNext(): Boolean = true

    override fun next(): T {
        if(!iterator.hasNext()) iterator = fields.iterator()
        return iterator.next()
    }
}

enum class Field {
    X, O;

    companion object {
        fun of(char: Char): Field {
            return if('#' == char) X
            else O
        }
    }

    val isTree: Boolean
        get() = this == X
}
