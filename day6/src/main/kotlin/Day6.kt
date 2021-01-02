import java.io.File

fun main() {
    println(day6(File("day6/input").readText()))
}

fun day6(input: String): Int {
    return input.split("\n\n")
        .asSequence()
        .map { it.replace("\n", "") }
        .map { it.toCharArray().toSet() }
        .sumBy { it.size }
}