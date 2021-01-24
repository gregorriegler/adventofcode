import java.io.File

fun main() {
    println(day10(File("day10/input").readText()))
    println(day10part2(File("day10/input").readText()))
}

fun day10(input: String): Int {
    return sortedJolts(input)
        .zipWithNext()
        .map { it.second - it.first }
        .groupingBy { it }
        .eachCount()
        .let { it[1]!! * (it[3]!! + 1) }
}

fun day10part2(input: String): Long {
    val jolts = sortedJolts(input)
    val pathsToJolt: MutableMap<Int, Long> = mutableMapOf(0 to 1L)

    jolts.drop(1).forEach {
        pathsToJolt[it] =
            (1..3).map { previous -> pathsToJolt.getOrDefault(it - previous, 0) }
                .sum()
    }

    return pathsToJolt.getValue(jolts.last())
}

fun sortedJolts(input: String): List<Int> = input.lines()
    .map { it.toInt() }
    .sorted()
    .let { listOf(0) + it }