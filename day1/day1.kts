#!/usr/bin/env kscript
@file:DependsOn("org.apache.commons:commons-math3:3.6.1")

import org.apache.commons.math3.util.CombinatoricsUtils.combinationsIterator
import java.io.File

main()

fun main() {
    trackDuration({
        println("" + loop("input.txt", 2020, -1) + " [loops]")
    })

    trackDuration({
        println("" + commonsMath("input.txt", 2, 2020) + " [apache commons-math]")
    })

    trackDuration({
        println("" + commonsMath("input.txt",3, 2020) + " [apache commons-math]")
    })

    trackDuration({
        println("" + recursiveGenerator("input.txt", 2, 2020) + " [recursive combinations generator]")
    })

    trackDuration({
        println("" + recursiveGenerator("input.txt", 3, 2020) + " [recursive combinations generator]")
    })
}

fun loop(report: String, amount: Int, orElse: Int): Int {
    val reader = File(report).bufferedReader()
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

fun commonsMath(report: String, n: Int, amount: Int): Int {
    return File(report)
        .readLines()
        .map { it.toInt() }
        .commonsCombinations(n)
        .first { it.hasSumEqualTo(amount) }
        .product()
}

fun recursiveGenerator(file: String, k: Int, sum: Int): Int {
    return File(file)
        .readLines()
        .map { it.toInt() }
        .combinations(k)
        .first { it.hasSumEqualTo(sum) }
        .product()
}

fun <T> List<T>.commonsCombinations(k: Int): Sequence<List<T>> {
    return combinationsIterator(this.size, k)
        .asSequence()
        .map { it -> it.map { this[it] } }
}

fun <T> List<T>.combinations(k: Int): Sequence<List<T>> {
    return recursiveCombinationGenerator(this, k)
}

fun <T> recursiveCombinationGenerator(input: List<T>, k: Int, building: MutableList<T> = mutableListOf()): Sequence<List<T>> {
    return sequence {
        if (building.size == k) {
            yield(building.toList())
        } else if (input.isNotEmpty()) {
            val first: T = input[0]
            val rest: List<T> = input.drop(1)
            yieldAll(recursiveCombinationGenerator(rest, k, building.plus(first).toMutableList()))
            yieldAll(recursiveCombinationGenerator(rest, k, building))
        }
    }
}

fun List<Int>.hasSumEqualTo(expected: Int): Boolean {
    return this.sum() == expected
}

fun Iterable<Int>.product(): Int {
    return reduce { acc, it -> acc * it }
}

fun trackDuration(func: () -> Unit) {
    val start = System.currentTimeMillis()
    func()
    println("took " + (System.currentTimeMillis() - start) + "ms")
}