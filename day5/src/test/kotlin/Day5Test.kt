import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
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
        assertThat(FrontOrBack.of('F').take(0..3)).isEqualTo(0..1)
        assertThat(FrontOrBack.of('F').take(0..127)).isEqualTo(0..63)
        assertThat(FrontOrBack.of('F').take(32..63)).isEqualTo(32..47)
    }

    @Test
    fun `test upper half rows`() {
        assertThat(FrontOrBack.of('B').take(0..3)).isEqualTo(2..3)
        assertThat(FrontOrBack.of('B').take(0..127)).isEqualTo(64..127)
        assertThat(FrontOrBack.of('B').take(32..47)).isEqualTo(40..47)
    }

    @Test
    fun `test column`() {
        assertThat(column("RLR")).isEqualTo(5)
        assertThat(column("RRR")).isEqualTo(7)
        assertThat(column("RLL")).isEqualTo(4)
    }
}

