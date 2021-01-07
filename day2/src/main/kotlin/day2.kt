import java.io.File

fun main() = countValidPasswords(File("day2/input.txt").readLines()) { CharPositionRule(it) }
    .run { println(this) }

fun countValidPasswords(lines: List<String>, ruleSupplier: (String) -> PasswordRule): Int =
    lines.count { validate(it, ruleSupplier) }

fun validate(line: String, ruleSupplier: (String) -> PasswordRule): Boolean {
    val (rule, password) = split(line, ruleSupplier)
    return rule.isValid(password)
}

fun split(line: String, ruleSupplier: (String) -> PasswordRule): Pair<PasswordRule, String> {
    val split = line.split(':')
    return Pair(ruleSupplier.invoke(split[0]), split[1].trim())
}

interface PasswordRule {
    fun isValid(password: String): Boolean
}

class CharsCountRule(private val spec: String) : PasswordRule {
    override fun isValid(password: String): Boolean {
        return password.filter { char() == it }
            .count()
            .let { it in range() }
    }

    private fun range(): IntRange = spec.split(' ')[0]
        .split('-')
        .map { it.toInt() }
        .let { it.first()..it.last() }

    private fun char() = spec.last()
}

class CharPositionRule(private val spec: String) : PasswordRule {
    override fun isValid(password: String): Boolean {
        return positions().filter { password.length > it && password[it] == char() }
            .count() == 1
    }

    private fun positions() = spec.split(' ')[0]
        .split('-')
        .map { it.toInt() - 1 }

    private fun char() = spec.last()
}
