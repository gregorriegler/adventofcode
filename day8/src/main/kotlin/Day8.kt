import java.io.File

fun main() {
    println(day8(File("day8/input").readText()))
}

fun day8(code: String): Int {
    return Program(code.lines())
        .run(InterruptBeforeSecondExecution())
}

class Program(val lines: List<String>) {
    fun run(interrupt: Interrupt = NoInterrupt()): Int {
        if (lines.isEmpty()) return 0
        if(lines[0].startsWith("acc")) {
            return lines[0].substringAfter(' ').toInt()
        }
        return 0
    }
}

interface Interrupt {
}

class NoInterrupt : Interrupt {

}

class InterruptBeforeSecondExecution : Interrupt {
}
