import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day10Test {

    @Test
    fun `acceptance part1`() {
        val result: Int = day10("16\n10\n15\n5\n1\n11\n7\n19\n6\n12\n4")

        assertThat(result).isEqualTo(7 * 5)
    }

    @Test
    fun `acceptance part2`() {
        assertThat(day10part2("1")).isEqualTo(1)
        assertThat(day10part2("1\n2")).isEqualTo(2)
        assertThat(day10part2("16\n10\n15\n5\n1\n11\n7\n19\n6\n12\n4")).isEqualTo(8)
        assertThat(day10part2("28\n33\n18\n42\n31\n14\n46\n20\n48\n47\n24\n23\n49\n45\n19\n38\n39\n11\n1\n32\n25\n35\n8\n17\n7\n9\n4\n2\n34\n10\n3")).isEqualTo(19208)
    }
}