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
            )
        ).isEqualTo(2)
    }

    @Test
    fun validateLine() {
        assertValid("1-3 a: abcde")
        assertValid("2-9 c: ccccccccc")
        assertInvalid("1-3 b: cdefg")
    }

    @Test
    fun passwordRule() {
        val rule = PasswordRule("1-1 a")
        assertThat(rule.isValid("a")).isTrue
        assertThat(rule.isValid("b")).isFalse
        assertThat(rule.isValid("aa")).isFalse
    }

    private fun assertValid(input: String) {
        assertThat(validate(input)).isTrue
    }

    private fun assertInvalid(input: String) {
        assertThat(validate(input)).isFalse
    }


}