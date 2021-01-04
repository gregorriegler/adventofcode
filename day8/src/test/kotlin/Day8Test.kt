import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day8Test {

    private val code = """
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

    @Disabled
    @Test
    fun acceptance() {
        val result: Int = day8(code)

        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `program starts with acc 0`() {
        assertThat(Program(emptyList()).run()).isEqualTo(0)
    }

    @Test
    fun `program increments acc`() {
        assertThat(Program(listOf("acc +1")).run()).isEqualTo(1)
    }

    @Test
    fun `program executes a nop instruction`() {
        assertThat(Program(listOf("nop +0")).run()).isEqualTo(0)
    }
}