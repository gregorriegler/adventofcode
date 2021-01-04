import java.io.File

fun main() {
    println(day8(File("day8/input").readText()))
    println(day8part2(File("day8/input").readText()))
}

fun day8(code: String): Int {
    return Program(code.lines(), InterruptTerminateBeforeSecondExecution()).run().value
}

fun day8part2(code: String): Int {
    val alterations = Alterations(code.lines())
    alterations.get().forEach {
        val result = Program(it, InterruptTerminateBeforeSecondExecution()).run()
        if (result is Success) return result.value
    }
    return -1
}

class Alterations(
    private val lines: List<String>
) {
    fun get(): List<List<String>> {
        val toggleIndexes = lines.withIndex()
            .filter { it.value.startsWith("jmp") || it.value.startsWith("nop") }
            .map { it.index }

        return toggleIndexes.map { toggleAt(lines, it) }
    }

    private fun toggleAt(lines: List<String>, toggleIndex: Int): List<String> {
        return lines.mapIndexed { index, line -> if (index == toggleIndex) toggle(line) else line }
            .toList()
    }

    private fun toggle(instruction: String): String {
        return if (instruction.startsWith("jmp")) {
            "nop" + instruction.drop(3)
        } else {
            "jmp" + instruction.drop(3)
        }
    }
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