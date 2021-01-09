import java.io.File

fun main() {
    println(day9(File("day9/input").readText()))
    println(day9part2(File("day9/input").readText()))
}

fun day9(input: String) = xmas(input.toListOfNumbers(), 25)

fun day9part2(input: String) = encryptionWeakness(input.toListOfNumbers(), 25)

fun String.toListOfNumbers() = this.lines().map(String::toLong)

fun encryptionWeakness(input: List<Long>, preambleLength: Int): Long {
    val xmas = xmas(input, preambleLength)
    return input.indices
        .map { input.drop(it) }
        .map { contiguousSumEquals(it, xmas) }
        .first { it.isNotEmpty() }
        .let { it.minOrNull()!! + it.maxOrNull()!! }
}

fun contiguousSumEquals(input: List<Long>, target: Long): List<Long> {
    var contiguousSum: Long = 0
    var i = 0
    while (contiguousSum < target) {
        contiguousSum += input[i]
        i++
    }
    if(contiguousSum == target) return input.take(i)
    return emptyList()
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

    private fun summable() = preamble
        .indices
        .toList()
        .dropLast(1)
        .map { preamble.drop(it) }
        .flatMap { list -> list.drop(1).map { it + list.first() } }
        .contains(target)
}
