import java.io.File

fun main() {
    println(day8(File("day8/input").readText()))
    println(day8part2(File("day8/input").readText()))
}

fun day8(code: String): Int {
    return Program(code.lines(), TerminateBeforeSecondExecution()).run().value
}

fun day8part2(code: String): Int {
    return Alterations(code.lines())
        .asSequence()
        .map { Program(it, TerminateBeforeSecondExecution()).run() }
        .first { it is Success }
        .value
}

class Alterations(
    private val lines: List<String>
) {
    fun asSequence(): Sequence<List<String>> {
        return lines.indices
            .map { toggleAt(lines, it) }
            .asSequence()
    }

    private fun toggleAt(lines: List<String>, toggleIndex: Int): List<String> {
        return lines.mapIndexed { index, line -> if (index == toggleIndex) Instruction(line).toggle() else line }
            .toList()
    }
}

class Program(
    private val lines: List<String>,
    private val interrupt: Interrupt = NoInterrupt()
) {

    private var acc = 0

    fun run(): Result<Int> {
        return if (processLines() is Success) {
            Success(acc)
        } else {
            Error(acc)
        }
    }

    private fun processLines() = processLine(0)

    private fun processLine(number: Int): Result<Unit> {
        if (number >= lines.size) return Success(Unit)
        if (!interrupt.continueProgram(number)) return Error(Unit)

        Instruction(lines[number]).let {
            return processLine(
                when {
                    it.isAcc() -> it.also { acc += it.number }.run { next(number) }
                    it.isJmp() -> jumpBy(number, it.number)
                    else -> next(number)
                }
            )
        }
    }

    private fun next(number: Int) = number + 1
    private fun jumpBy(number: Int, jmp: Int) = number + jmp
}

class Instruction(instruction: String) {
    private val type:String
    val number:Int

    init {
        instruction.split(' ').let {
            this.type = it.first()
            this.number = it.last().toInt()
        }
    }

    fun isAcc() :Boolean = type == "acc"
    fun isJmp() :Boolean = type == "jmp"
    private fun isNop(): Boolean = type == "nop"

    fun toggle(): String {
        return when {
            isJmp() -> "nop $number"
            isNop() -> "jmp $number"
            else -> "$type $number"
        }
    }
}

interface Interrupt {
    fun continueProgram(number: Int): Boolean
}

class NoInterrupt : Interrupt {
    override fun continueProgram(number: Int) = true

}

class TerminateBeforeSecondExecution : Interrupt {
    private val visitedLines: MutableList<Int> = mutableListOf()

    override fun continueProgram(number: Int): Boolean {
        return if (visitedLines.contains(number)) false
        else {
            visitedLines.add(number)
            true
        }
    }
}

sealed class Result<T>(open val value: T)
data class Success<T>(override val value: T) : Result<T>(value)
data class Error<T>(override val value: T) : Result<T>(value)