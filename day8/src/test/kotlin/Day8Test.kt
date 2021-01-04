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
    fun instructions() = listOf(
        Triple("starts at 0", emptyList(), 0),
        Triple("increments acc", listOf("acc +1"), 1),
        Triple("increments acc by 2", listOf("acc +2"), 2),
        Triple("handles many instructions", listOf("acc +1", "acc +1"), 2),
        Triple("nop does nothing", listOf("nop +0", "acc +1"), 1),
        Triple("jump +1 does nothing", listOf("jmp +1", "acc +1"), 1),
        Triple("jumps forward", listOf("jmp +2", "acc +3", "acc +1"), 1),
        Triple("ends bad jump", listOf("jmp +2"), 0),
    )
        .map { (description, lines, expectedAcc) ->
            DynamicTest.dynamicTest(description) {
                assertThat(Program(lines).run()).isEqualTo(expectedAcc)
            }
        }

    @Test
    fun interrupt() {
        val lineTracker = LineTracker()
        Program(listOf("jmp +2", "acc +3", "acc +1", "nop +0"), lineTracker).run()

        assertThat(lineTracker.visitedLines).containsExactly(0, 2, 3)
    }
}

class LineTracker : Interrupt {
    val visitedLines: MutableList<Int> = mutableListOf()

    override fun hit(number: Int) {
        visitedLines.add(number)
    }
}