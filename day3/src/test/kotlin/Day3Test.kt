import Field.O
import Field.X
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun acceptance() {
        val result: Long = day3(woodInput())

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun acceptance_part2() {
        val result: Long = day3Part2(woodInput())

        assertThat(result).isEqualTo(336)
    }

    @Test
    fun `right 3 down 1 slope`() {
        val slope = RightDownSlope(Wood(woodInput()), 3, 1)

        assertThat(slope.iterator().asSequence().toList()).containsExactly(
            O, X, O, X, X, O, X, X, X, X
        )
    }

    @Test
    fun wood() {
        val wood = Wood(
            """
            .#
            #.
            """.trimIndent()
        )

        assertThat(wood.at(0, 0)).isEqualTo(O)
        assertThat(wood.at(0, 1)).isEqualTo(X)
        assertThat(wood.at(0, 2)).isEqualTo(O)
        assertThat(wood.at(0, 1)).isEqualTo(X)

        assertThat(wood.at(1, 0)).isEqualTo(X)
        assertThat(wood.at(1, 1)).isEqualTo(O)
        assertThat(wood.at(1, 2)).isEqualTo(X)
        assertThat(wood.at(1, 1)).isEqualTo(O)

        assertThat(wood.at(2, 0)).isNull()
    }

    @Test
    fun `looping iterator`() {
        val iterator = LoopingIterator(0..2)
        assertThat(iterator.hasNext()).isEqualTo(true)
        assertThat(iterator.next()).isEqualTo(0)
        assertThat(iterator.next()).isEqualTo(1)
        assertThat(iterator.next()).isEqualTo(2)
        assertThat(iterator.next()).isEqualTo(0)
        assertThat(iterator.next()).isEqualTo(1)
    }

    private fun woodInput() =
        """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
        """.trimIndent()
}