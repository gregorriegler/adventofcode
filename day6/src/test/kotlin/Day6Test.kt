import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun acceptance() {
        val result: Int = day6(
            """
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
        )

        assertThat(result).isEqualTo(11)
    }

}