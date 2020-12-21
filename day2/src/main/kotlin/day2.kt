import java.io.File

fun main() {
    println(
        countValidPasswords(
            File("day2/input.txt").readLines()
        ) { CharsCountRule(it) }
    )
}

fun countValidPasswords(lines: List<String>, rulesSupplier: (String) -> PasswordRule): Int {
    return lines.count { validate(it, rulesSupplier) }
}

fun validate(line: String, ruleSupplier: (String) -> PasswordRule): Boolean {
    val (rule, password) = split(line, ruleSupplier)
    return rule.isValid(password)
}

fun split(line: String, ruleSupplier: (String) -> PasswordRule): Pair<PasswordRule, String> {
    val split = line.split(':')
    return Pair(ruleSupplier.invoke(split[0]), split[1])
}

interface PasswordRule {
    fun isValid(password: String): Boolean
}

class CharsCountRule(private val spec: String) : PasswordRule {

    override fun isValid(password: String): Boolean {
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
