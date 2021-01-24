import java.io.File

fun main() {
    println(day10(File("day10/input").readText()))
}

fun day10(input: String): Int {
    return input.lines()
        .map { it.toInt() }
        .sorted()
        .let { listOf(0) + it }
        .windowed(2, 1)
        .map { it[1] - it[0] }
        .groupingBy { it }
        .eachCount()
        .let { it[1]!! * (it[3]!! + 1) }
}

