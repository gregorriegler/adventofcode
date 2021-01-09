import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day9Test {

    private val input = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()

    @Test
    fun acceptance() {
        val result: Int = day9(input)

        assertThat(result).isEqualTo(127)
    }

}