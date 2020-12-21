import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class day2test {

    @Test
    fun acceptance() {
        assertThat(
            countValidPasswords(
                """
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """.trimIndent().lines()
            ) { CharsCountRule(it) }
        ).isEqualTo(2)
    }

    @Test
    fun validateLine() {
        assertValid("1-3 a: abcde") { CharsCountRule(it) }
        assertValid("2-9 c: ccccccccc") { CharsCountRule(it) }
        assertInvalid("1-3 b: cdefg") { CharsCountRule(it) }
    }

    @Test
    fun charsCountPasswordRule() {
        val rule = CharsCountRule("1-1 a")
        assertThat(rule.isValid("a")).isTrue
        assertThat(rule.isValid("b")).isFalse
        assertThat(rule.isValid("aa")).isFalse
    }

    private fun assertValid(input: String, ruleSupplier: (String) -> PasswordRule) {
        assertThat(validate(input, ruleSupplier)).isTrue
    }

    private fun assertInvalid(input: String, ruleSupplier: (String) -> PasswordRule) {
        assertThat(validate(input, ruleSupplier)).isFalse
    }


}