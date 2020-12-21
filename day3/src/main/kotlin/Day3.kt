import java.io.File

fun main() {
    println(day3(File("day3/input").readText()))
}

fun day3(input: String) = Right3Down1Slope(Wood(input)).count { it.isTree }