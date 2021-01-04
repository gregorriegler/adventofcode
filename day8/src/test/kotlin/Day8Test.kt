import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory


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

    @TestFactory
    fun program() = listOf(
        Triple("starts at 0", emptyList(), 0),
        Triple("nop does nothing", listOf("nop +0"), 0),
        Triple("increments acc", listOf("acc +1"), 1),
        Triple("increments acc by 2", listOf("acc +2"), 2),
    )
        .map { (description, lines, expectedAcc) ->
            DynamicTest.dynamicTest(description) {
                assertThat(Program(lines).run()).isEqualTo(expectedAcc)
            }
        }
}