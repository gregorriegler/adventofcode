import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Test {

    private val charsCountRule: (String) -> PasswordRule = { CharsCountRule(it) }
    private var charPositionRule: (String) -> PasswordRule = { CharPositionRule(it) }

    @Test
    fun acceptance() {
        assertThat(
            countValidPasswords(
                """
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """.trimIndent().lines(), charsCountRule
            )
        ).isEqualTo(2)

        assertThat(
            countValidPasswords(
                """
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """.trimIndent().lines(), charPositionRule
            )
        ).isEqualTo(1)
    }

    @Test
    fun validateLine_charsCountRule() {
        assertValid("1-3 a: abcde", charsCountRule)
        assertValid("2-9 c: ccccccccc", charsCountRule)
        assertInvalid("1-3 b: cdefg", charsCountRule)
    }

    @Test
    fun charsCountRule() {
        val rule = CharsCountRule("1-1 a")
        assertThat(rule.isValid("a")).isTrue
        assertThat(rule.isValid("b")).isFalse
        assertThat(rule.isValid("aa")).isFalse
    }

    @Test
    fun validateLine_charPositionRule() {
        assertValid("1-3 a: abcde", charPositionRule)
        assertInvalid("1-3 b: cdefg", charPositionRule)
        assertInvalid("2-9 c: ccccccccc", charPositionRule)
    }

    @Test
    fun charPositionRule() {
        val rule = CharPositionRule("1-3 a")
        assertThat(rule.isValid("bbb")).isFalse
        assertThat(rule.isValid("bb")).isFalse
        assertThat(rule.isValid("b")).isFalse
        assertThat(rule.isValid("")).isFalse
        assertThat(rule.isValid("a")).isTrue
        assertThat(rule.isValid("baa")).isTrue
        assertThat(rule.isValid("aba")).isFalse
    }

    private fun assertValid(input: String, ruleSupplier: (String) -> PasswordRule) {
        assertThat(validate(input, ruleSupplier)).isTrue
    }

    private fun assertInvalid(input: String, ruleSupplier: (String) -> PasswordRule) {
        assertThat(validate(input, ruleSupplier)).isFalse
    }


}