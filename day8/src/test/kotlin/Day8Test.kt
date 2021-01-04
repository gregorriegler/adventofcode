import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day8Test {

    private val program = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

    @Test
    @Disabled
    fun acceptance() {
        val result: Int = day8(program)

        assertThat(result).isEqualTo(5)
    }
}