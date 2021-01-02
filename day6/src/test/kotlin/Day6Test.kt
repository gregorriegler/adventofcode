import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Test {

    val input = """
            abc

            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
        """.trimIndent()

    @Test
    fun acceptance_part1() {
        val result: Int = day6(input)

        assertThat(result).isEqualTo(11)
    }

    @Test
    fun acceptance_part2() {
        val result: Int = day6part2(input)

        assertThat(result).isEqualTo(6)
    }


}