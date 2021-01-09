import java.io.File

fun main() {
    println(day9(File("day9/input").readText()))
    println(day9part2(File("day9/input").readText()))
}

fun day9(input: String) = xmas(input.toNumberSequence(), 25)

fun day9part2(input: String) = encryptionWeakness(input.toNumberSequence(), 25)

fun String.toNumberSequence() = this.lineSequence().map(String::toLong)

fun encryptionWeakness(input: Sequence<Long>, preambleLength: Int): Long {
    val xmas = xmas(
        input, preambleLength
    )
    return 62
}

fun xmas(input: Sequence<Long>, preambleLength: Int) = input
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
