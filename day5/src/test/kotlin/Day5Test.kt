import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun acceptance() {
        val result: Int = day5("")

        assertThat(result).isEqualTo(1)
    }
}


