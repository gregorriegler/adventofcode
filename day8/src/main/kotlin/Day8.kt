import java.io.File

fun main() {
    println(day8(File("day8/input").readText()))
}

fun day8(code: String): Int {
    return Program(code.lines())
        .run(InterruptBeforeSecondExecution())
}

class Program(val lines: List<String>) {

    private var acc = 0

    fun run(interrupt: Interrupt = NoInterrupt()): Int {
        processLine(0)
        return acc
    }

    private fun processLine(number: Int) {
        if (number >= lines.size) return
        val instruction = lines[number]

        if (instruction.startsWith("acc")) {
            acc += instruction.substringAfter(' ').toInt()
        } else if (instruction.startsWith("jmp")) {
            val jmp = instruction.substringAfter(' ').toInt()
        }

        processLine(number + 1)
    }

}

interface Interrupt {
}

class NoInterrupt : Interrupt {

}

class InterruptBeforeSecondExecution : Interrupt {
}
