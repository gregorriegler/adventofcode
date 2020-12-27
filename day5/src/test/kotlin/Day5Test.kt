import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    @Disabled
    fun acceptance() {
        val result: Int = day5(
            """
            BFFFBBFRRR
            FFFBBBFRRR
            BBFFBBFRLL
        """.trimIndent()
        )

        assertThat(result).isEqualTo(820)
    }

    @Test
    @Disabled
    fun `test seatId`() {
        assertThat(seatId("BFFFBBFRRR")).isEqualTo(567)
        assertThat(seatId("FFFBBBFRRR")).isEqualTo(119)
        assertThat(seatId("BBFFBBFRLL")).isEqualTo(820)
    }

    @Test
    fun `test row`() {
        assertThat(row("BFFFBBF")).isEqualTo(70)
        assertThat(row("FFFBBBF")).isEqualTo(14)
        assertThat(row("BBFFBBF")).isEqualTo(102)
    }

    @Test
    fun `test lower half rows`() {
        assertThat(Half.of('F').take(0..3)).isEqualTo(0..1)
        assertThat(Half.of('F').take(0..127)).isEqualTo(0..63)
        assertThat(Half.of('F').take(32..63)).isEqualTo(32..47)
    }

    @Test
    fun `test upper half rows`() {
        assertThat(Half.of('B').take(0..3)).isEqualTo(2..3)
        assertThat(Half.of('B').take(0..127)).isEqualTo(64..127)
        assertThat(Half.of('B').take(32..47)).isEqualTo(40..47)
    }

    @Test
    @Disabled
    fun `test column`() {
        assertThat(column("RLR")).isEqualTo(5)
        assertThat(column("RRR")).isEqualTo(7)
        assertThat(column("RLL")).isEqualTo(4)
    }

}

fun seatId(seat: String): Int {
    val rowId = row(seat.take(6))
    val columnId = column(seat.substring(6))
    return rowId * columnId
}

fun row(rowInput: String): Int {
    return rowInput.map { Half.of(it) }
        .fold(0..127) { range, half -> half.take(range) }
        .first
}

fun column(columnInput: String): Int {
    TODO("Not yet implemented")
}

enum class Half {
    Lower {
        override fun take(range: IntRange): IntRange {
            return range.first until range.elementAt(range.count() / 2)
        }
    },
    Upper {
        override fun take(range: IntRange): IntRange {
            return range.elementAt(range.count() / 2) .. range.last
        }
    };

    abstract fun take(range: IntRange): IntRange

    companion object {
        fun of(input: Char): Half {
            return when (input) {
                'F' -> Lower
                'B' -> Upper
                else -> throw IllegalArgumentException("only F or B allowed")
            }
        }
    }
}


