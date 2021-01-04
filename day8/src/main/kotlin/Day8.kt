import java.io.File

fun main() {
    println(day8(File("day8/input").readText()))
}

fun day8(code: String): Int {
    return Program(code.lines(), InterruptStopBeforeSecondExecution()).run()
}

class Program(
    private val lines: List<String>,
    private val interrupt: Interrupt = NoInterrupt()
) {

    private var acc = 0

    fun run(): Int {
        executeLine(0)
        return acc
    }

    private fun executeLine(number: Int) {
        if (number >= lines.size) return
        if (!interrupt.continueProgram(number)) return

        executeLine(instruction(lines[number], number))
    }

    private fun instruction(instruction: String, number: Int): Int {
        return when {
            isAcc(instruction) -> {
                acc += extractNumber(instruction)
                next(number)
            }
            isJmp(instruction) -> {
                jumpBy(number, extractNumber(instruction))
            }
            else -> next(number)
        }
    }

    private fun isJmp(instruction: String) = instruction.startsWith("jmp")

    private fun isAcc(instruction: String) = instruction.startsWith("acc")

    private fun extractNumber(instruction: String) = instruction.substringAfter(' ').toInt()

    private fun next(number: Int) = number + 1

    private fun jumpBy(number: Int, jmp: Int) = number + jmp

}

interface Interrupt {
    fun continueProgram(number: Int): Boolean
}

class NoInterrupt : Interrupt {
    override fun continueProgram(number: Int) = true

}

class InterruptStopBeforeSecondExecution : Interrupt {
    override fun continueProgram(number: Int) = true
}
