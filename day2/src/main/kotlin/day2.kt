import java.io.File

fun main() = countValidPasswords(File("day2/input.txt").readLines()) { CharPositionRule(it) }
    .run { println(this) }

fun countValidPasswords(lines: List<String>, ruleSupplier: (String) -> PasswordRule): Int =
    lines.count { validate(it, ruleSupplier) }

fun validate(line: String, ruleSupplier: (String) -> PasswordRule): Boolean =
    split(line, ruleSupplier)
        .let { (rule, password) -> rule.isValid(password) }

fun split(line: String, ruleSupplier: (String) -> PasswordRule): Pair<PasswordRule, String> =
    line.split(':').let { Pair(ruleSupplier.invoke(it[0]), it[1].trim()) }

interface PasswordRule {
    fun isValid(password: String): Boolean
}

class CharsCountRule(private val spec: String) : PasswordRule {
    override fun isValid(password: String): Boolean =
        password.filter { char() == it }
            .count()
            .let { it in range() }

    private fun range(): IntRange = spec.split(' ')[0]
        .split('-')
        .map { it.toInt() }
        .let { it.first()..it.last() }

    private fun char() = spec.last()
}

class CharPositionRule(private val spec: String) : PasswordRule {
    override fun isValid(password: String): Boolean =
        positions().filter { password.length > it && password[it] == char() }
            .count() == 1

    private fun positions() = spec.split(' ')[0]
        .split('-')
        .map { it.toInt() - 1 }

    private fun char() = spec.last()
}
