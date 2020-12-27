import java.io.File

fun main() {
    println(day5(File("day5/input").readText()))
}

fun day5(input: String): Int {
    return input.lines()
        .map { seatId(it) }
        .maxOrNull()!!
}

fun seatId(seat: String): Int {
    val rowId = row(seat.take(6))
    val columnId = column(seat.substring(7))
    return rowId * 8 + columnId
}

fun row(rowInput: String): Int {
    return rowInput.map { FrontOrBack.of(it) }
        .fold(0..127) { range, frontOrBack -> frontOrBack.take(range) }
        .first
}

fun column(columnInput: String): Int {
    return columnInput.map { LeftOrRight.of(it) }
        .fold(0..8) { range, leftOrRight -> leftOrRight.take(range) }
        .first
}

enum class FrontOrBack(private val half: Half) {

    F(Half.Lower),
    B(Half.Upper);

    fun take(range: IntRange) = half.take(range)

    companion object {
        fun of(input: Char): FrontOrBack {
            return when (input) {
                'F' -> F
                'B' -> B
                else -> throw IllegalArgumentException("only F or B allowed")
            }
        }
    }
}

enum class LeftOrRight(private val half: Half) {

    L(Half.Lower),
    R(Half.Upper);

    fun take(range: IntRange) = half.take(range)

    companion object {
        fun of(input: Char): LeftOrRight {
            return when (input) {
                'L' -> L
                'R' -> R
                else -> throw IllegalArgumentException("only L or R allowed")
            }
        }
    }
}

enum class Half {
    Lower {
        override fun take(range: IntRange) = range.first until range.elementAt(range.count() / 2)
    },
    Upper {
        override fun take(range: IntRange) = range.elementAt(range.count() / 2)..range.last
    };

    abstract fun take(range: IntRange): IntRange
}