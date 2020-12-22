import java.io.File

fun main() {
    println(day4(File("day4/input").readText()))
}

fun day4(input: String): Int {
    return splitByEmptyLine(input)
        .map { scanPassportData(it) }
        .filter { valid(it) }
        .count()
}

fun splitByEmptyLine(input: String): List<String> = input.split(Regex("\n[\\s]*\n"))
    .map { it.trim() }
    .filter { it.isNotEmpty() }

fun scanPassportData(input: String): PassportData {
    return input.split(Regex("[\\s]+"))
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .map { parsePair(it) }
        .let { PassportData(it) }
}

fun parsePair(input: String): Pair<Field, String> {
    return input.split(":")
        .map { it.trim() }
        .let { Pair(Field.valueOf(it[0]), it[1]) }
}

fun valid(passport: PassportData): Boolean =
    Field.values().all { it.valid(passport.value[it]) }

class PassportData(input: List<Pair<Field, String>>) {
    constructor(vararg input: Pair<Field, String>) : this(input.toList())

    val value: Map<Field, String> = input.toMap()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PassportData

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}

enum class Field {

    byr, iyr, eyr, hgt, hcl, ecl, pid,

    cid {
        override fun valid(value: String?): Boolean = true
    };

    open fun valid(value: String?): Boolean = value != null
}