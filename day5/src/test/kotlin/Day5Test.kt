import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    @Disabled
    fun acceptance() {
        val result: Int = day5("""
            BFFFBBFRRR
            FFFBBBFRRR
            BBFFBBFRLL
        """.trimIndent())

        assertThat(result).isEqualTo(820)
    }
}


