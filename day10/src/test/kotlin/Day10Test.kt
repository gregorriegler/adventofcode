import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day10Test {

    private val input = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

    @Test
    fun acceptance() {
        val result: Int = day10(input)

        assertThat(result).isEqualTo(7 * 5)
    }
}