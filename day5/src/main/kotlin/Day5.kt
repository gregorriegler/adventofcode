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
    val row = row(seat.take(7))
    val column = column(seat.substring(7))
    return row * 8 + column
}

fun row(rowInput: String): Int {
    return rowInput.map { FrontOrBack.of(it) }
        .fold(0..127) { range, frontOrBack -> frontOrBack.take(range) }
        .first
}

fun column(columnInput: String): Int {
    return columnInput.map { LeftOrRight.of(it) }
        .fold(0..7) { range, leftOrRight -> leftOrRight.take(range) }
        .first
}

enum class FrontOrBack(private val half: Half) {
    F(Half.Lower),
    B(Half.Upper);

    fun take(range: IntRange) = half.take(range)

    companion object {
        fun of(input: Char): FrontOrBack = valueOf(input.toString())
    }
}

enum class LeftOrRight(private val half: Half) {
    L(Half.Lower),
    R(Half.Upper);

    fun take(range: IntRange) = half.take(range)

    companion object {
        fun of(input: Char): LeftOrRight = valueOf(input.toString())
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