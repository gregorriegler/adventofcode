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
        lines.forEach { process(it) }
        return acc
    }

    private fun process(instruction: String) {
        if (instruction.startsWith("acc")) {
            acc += instruction.substringAfter(' ').toInt()
        }
    }
}

interface Interrupt {
}

class NoInterrupt : Interrupt {

}

class InterruptBeforeSecondExecution : Interrupt {
}
