import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day10Test {

    private val input = "16\n10\n15\n5\n1\n11\n7\n19\n6\n12\n4"

    @Test
    fun acceptance() {
        val result: Int = day10(input)

        assertThat(result).isEqualTo(7 * 5)
    }
}