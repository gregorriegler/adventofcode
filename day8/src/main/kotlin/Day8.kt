import java.io.File

fun main() {
    println(day8(File("day8/input").readText()))
}

fun day8(code: String): Int {
    return Program(code.lines(), InterruptTerminateBeforeSecondExecution()).run().value
}

class Program(
    private val lines: List<String>,
    private val interrupt: Interrupt = NoInterrupt()
) {

    private var acc = 0

    fun run(): Result<Int> {
        return if (executeLine(0) is Success) {
            Success(acc)
        } else {
            Error(acc)
        }
    }

    private fun executeLine(number: Int): Result<Unit> {
        if (number >= lines.size) return Success(Unit)
        if (!interrupt.continueProgram(number)) return Error(Unit)

        return executeLine(instruction(lines[number], number))
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

class InterruptTerminateBeforeSecondExecution : Interrupt {
    private val visitedLines: MutableList<Int> = mutableListOf()

    override fun continueProgram(number: Int): Boolean {
        return if (visitedLines.contains(number)) false
        else {
            visitedLines.add(number)
            true
        }
    }
}

open class Result<T>(open val value: T)
data class Success<T>(override val value: T) : Result<T>(value)
data class Error<T>(override val value: T) : Result<T>(value)