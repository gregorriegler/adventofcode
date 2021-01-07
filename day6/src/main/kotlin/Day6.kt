import java.io.File

fun main() {
    println(day6(File("day6/input").readText()))
    println(day6part2(File("day6/input").readText()))
}

fun day6(input: String): Int =
    input.split("\n\n")
        .map { it.distinctChars() }
        .sumBy { it.size }

fun day6part2(input: String): Int =
    input.split("\n\n")
        .map { block ->
            block.distinctChars()
                .filter { char -> block.lines().all { it.contains(char) } }
        }
        .sumBy { it.size }

fun String.distinctChars(): Set<Char> {
    return this.replace("\n", "").toCharArray().toSet()
}
