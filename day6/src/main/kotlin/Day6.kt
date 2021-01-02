import java.io.File

fun main() {
    println(day6(File("day6/input").readText()))
    println(day6part2(File("day6/input").readText()))
}

fun day6(input: String): Int {
    return input.split("\n\n")
        .asSequence()
        .map { it.distinctChars() }
        .sumBy { it.size }
}

fun day6part2(input: String): Int {
    return input.split("\n\n")
        .asSequence()
        .map { Pair(
            it.distinctChars(),
            it.split('\n')
            )}
        .map { pair -> pair.first
            .stream()
            .filter{char -> pair.second.all { it.contains(char) }}
            .count()
        }
        .sum().toInt()

}

fun String.distinctChars(): Set<Char> {
    return this.replace("\n", "").toCharArray().toSet()
}
