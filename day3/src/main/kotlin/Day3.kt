import java.io.File

fun main() {
    println(day3(File("day3/input").readText()))
    println("---")
    println(day3Part2(File("day3/input").readText()))
}

fun day3(input: String) = solution(
    input,
    Pair(3, 1)
)

fun day3Part2(input: String): Long = solution(
    input,
    Pair(1, 1),
    Pair(3, 1),
    Pair(5, 1),
    Pair(7, 1),
    Pair(1, 2)
)

fun solution(input: String, vararg list: Pair<Int, Int>): Long =
    Wood(input).let { wood ->
        list.map { (nextCol, nextRow) -> RightDownSlope(wood, nextCol, nextRow) }
            .map { slope -> slope.count { it.isTree }.toLong() }
            .reduce { accumulation: Long, count -> accumulation * count }
    }