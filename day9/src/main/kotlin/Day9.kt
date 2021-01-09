import java.io.File

fun main() {
    println(day9(File("day9/input").readText()))
    println(day9part2(File("day9/input").readText()))
}

fun day9(input: String) = xmas(input.toListOfNumbers(), 25)

fun day9part2(input: String) = encryptionWeakness(input.toListOfNumbers(), 25) // 268878261

fun String.toListOfNumbers() = this.lines().map(String::toLong)

fun encryptionWeakness(input: List<Long>, preambleLength: Int): Long {
    val xmas = xmas(input, preambleLength)
    return input.indices
        .map { input.drop(it) }
        .flatMap { list -> (1 until list.size).map { list.take(it) } }
        .first { it.sum() == xmas }
        .let { it.maxOrNull()!! + it.minOrNull()!! }
}

fun xmas(input: List<Long>, preambleLength: Int) =
    input
        .windowed(preambleLength + 1, 1)
        .map {
            Xmas(
                target = it.last(),
                preamble = it.take(preambleLength)
            )
        }
        .first { it.notSummable() }
        .target

data class Xmas(
    val target: Long,
    val preamble: List<Long>
) {

    fun notSummable(): Boolean = !summable()

    private fun summable() = preamble.any { target - it in preamble }
}
