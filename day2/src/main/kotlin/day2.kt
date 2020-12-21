import java.io.File

fun main() {
    println(countValidPasswords(File("day2/input.txt").readLines()))
}

fun countValidPasswords(lines: List<String>): Int {
    return lines.count { validate(it) }
}


fun validate(line: String): Boolean {
    val pair: Pair<PasswordRule, String> = split(line)
    return pair.first.isValid(pair.second)
}

fun split(line: String): Pair<PasswordRule, String> {
    val split = line.split(':')
    return Pair(PasswordRule(split[0]), split[1])
}

class PasswordRule(private val spec: String) {

    fun isValid(password: String): Boolean {
        val (min, max) = range()
        return password.filter { char() == it }
            .count()
            .let { it in min..max }
    }

    private fun range() = spec.split(' ')[0]
        .split('-')
        .map { it.toInt() }

    private fun char() = spec.last()
}
