import java.io.File

fun main() {
    println(day9(File("day9/input").readText()))
}

fun day9(input: String): Long = xmas(input, 25)

fun xmas(input: String, preambleLength: Int) = input.lineSequence()
    .map(String::toLong)
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
