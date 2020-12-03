#!/usr/bin/env kscript
@file:DependsOn("org.apache.commons:commons-math3:3.6.1")

import org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator
import java.io.File

main()

fun main() {
    assert(productOfFirstTwoEntriesThatSumToOrElse(2020, "report", -1) == 514579)
    assert(productOfFirstNEntriesWhoseSumIsNew(2, 2020, "report") == 514579)

    trackDuration({
        println(productOfFirstTwoEntriesThatSumToOrElse(2020, "input.txt", -1))
    })

    trackDuration({
        println(productOfFirstNEntriesWhoseSumIs(2, 2020, "input.txt"))
    })

    trackDuration({
        println(productOfFirstNEntriesWhoseSumIs(3, 2020, "input.txt"))
    })

    trackDuration({
        println(productOfFirstNEntriesWhoseSumIsNew(3, 2020, "input.txt"))
    })
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

fun productOfFirstNEntriesWhoseSumIsNew(n: Int, amount: Int, pathToReport: String): Int {
    val lines = File(pathToReport).readLines().map { it.toInt() }

    return lines.combinations(n)
        .first { it.sum() == amount }
        .reduce { acc, i -> i * acc }
}

fun <T> Iterable<T>.combinations(n: Int): Sequence<List<T>> = sequence {
    val pool = this@combinations.toList()
    val poolSize = pool.size
    if (n > poolSize) return@sequence
    yield(pool.take(n))
    val indices = IntArray(n) { it }

    while (indices[0] != poolSize - n) {
        var i = n - 1
        while (indices[i] == i + poolSize - n) i--

        indices[i]++
        for (j in (i + 1) until n) {
            indices[j] = indices[j - 1] + 1
        }
        yield(indices.map { pool[it] })
    }
}

fun trackDuration(func: () -> Unit) {
    val start = System.currentTimeMillis()
    func()
    println("took " + (System.currentTimeMillis() - start) + "ms")
}
