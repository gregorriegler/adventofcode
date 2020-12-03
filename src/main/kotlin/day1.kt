import org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator
import java.io.File

fun main() {
    val start = System.currentTimeMillis()
    println(productOfFirstTwoEntriesThatSumToOrElse(2020, "day1/input.txt", -1))
    println("took " + (System.currentTimeMillis() - start))

    val start2 = System.currentTimeMillis()
    println(productOfFirstNEntriesWhoseSumIs(2, 2020, "day1/input.txt"))
    println("took " + (System.currentTimeMillis() - start2))

    println(productOfFirstNEntriesWhoseSumIs(3, 2020, "day1/input.txt"))
}

fun productOfFirstTwoEntriesThatSumToOrElse(amount: Int, pathToReport: String, orElse: Int): Int {
    val reader = File(pathToReport).bufferedReader()
    val read = arrayListOf(reader.readLine()?.toInt() ?: return orElse)
    while (true) {
        val next = reader.readLine()?.toInt() ?: return orElse
        read.map { Pair(it, next) }
            .forEach {
                if (it.first + it.second == amount) return it.first * it.second
            }
        read.add(next)
    }
}

fun productOfFirstNEntriesWhoseSumIs(n: Int, amount: Int, pathToReport: String): Int {
    val lines = File(pathToReport).readLines().map { it.toInt() }

    return combinationsIterator(lines.size, n)
        .asSequence()
        .map { it.map { x -> lines[x] } }
        .first { it.sum() == amount }
        .reduce { product, it -> product * it }
}
