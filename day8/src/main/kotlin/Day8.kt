import java.io.File

fun main() {
    println(day8(File("day8/input").readText()))
}

fun day8(code: String): Int {
    val program = Program(code.lines())
    return program.run(InterruptBeforeSecondExecution())
}

class Program(lines: List<String>) {
    fun run(interrupt: Interrupt): Int {
        return 5
    }

}

interface Interrupt {
}


class InterruptBeforeSecondExecution : Interrupt {
}
