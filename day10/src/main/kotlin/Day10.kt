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
    val adapters = sortedJolts(input)
    val pathsToAdapter: MutableMap<Int, Long> = mutableMapOf(0 to 1L)

    adapters.drop(1).forEach {
        pathsToAdapter[it] =
            (1..3).map { previous -> pathsToAdapter.getOrDefault(it - previous, 0) }
                .sum()
    }

    return pathsToAdapter.getValue(adapters.last())
}

fun sortedJolts(input: String): List<Int> = input.lines()
    .map { it.toInt() }
    .sorted()
    .let { listOf(0) + it }