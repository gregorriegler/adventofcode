import java.io.File

fun main() {
    println(day3(File("day3/input").readText()))
    println(day3Part2(File("day3/input").readText()))
}

fun day3(input: String) = RightDownSlope(Wood(input), 3, 1).count { it.isTree }

fun day3Part2(input: String): Int {
    val wood = Wood(input)
    return listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2)
    ).map { (nextCol, nextRow) -> RightDownSlope(wood, nextCol, nextRow) }
        .map { slope -> slope.toList().count { it.isTree } }
        .reduce { acc, it -> acc * it }
}