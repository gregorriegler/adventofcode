import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day7Test {

    val input = """
                light red bags contain 1 bright white bag, 2 muted yellow bags.
                dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                bright white bags contain 1 shiny gold bag.
                muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                faded blue bags contain no other bags.
                dotted black bags contain no other bags.    
                """.trimIndent()

    @Test
    @Disabled
    fun acceptance() {
        assertThat(day7(this.input)).isEqualTo(4)
    }

    @Test
    fun toRule() {
        assertThat("faded blue bags contain no other bags.".toRule())
            .isEqualTo(LuggageRule("faded blue", emptyMap()))

        assertThat("faded blue bags contain 1 shiny gold bag.".toRule())
            .isEqualTo(LuggageRule("faded blue", mapOf(Pair("shiny gold", 1))))

        assertThat("faded blue bags contain 2 shiny gold bags.".toRule())
            .isEqualTo(LuggageRule("faded blue", mapOf(Pair("shiny gold", 2))))

        assertThat("faded blue bags contain 3 dotted black bags.".toRule())
            .isEqualTo(LuggageRule("faded blue", mapOf(Pair("dotted black", 3))))

        assertThat("light red bags contain 1 bright white bag, 2 muted yellow bags.".toRule())
            .isEqualTo(LuggageRule("light red", mapOf(
                Pair("bright white", 1),
                Pair("muted yellow", 2),
            )))
    }
}