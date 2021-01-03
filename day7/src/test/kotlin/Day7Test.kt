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
    fun acceptance() {
        assertThat(day7(this.input)).isEqualTo(4)
        assertThat(day7part2(this.input)).isEqualTo(32)
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
            .isEqualTo(
                LuggageRule(
                    "light red", mapOf(
                        Pair("bright white", 1),
                        Pair("muted yellow", 2),
                    )
                )
            )
    }

    @Test
    fun hasContainingBag() {
        val rule = LuggageRule(
            "light red", mapOf(
                Pair("bright white", 1),
                Pair("muted yellow", 2),
            )
        )

        assertThat(rule.hasContainingBag("not containing")).isFalse
        assertThat(rule.hasContainingBag("bright white")).isTrue
        assertThat(rule.hasContainingBag("muted yellow")).isTrue
    }

    @Test
    fun parentsRecursively() {
        assertThat(parentsRecursively("bag", emptyList())).isEmpty()

        assertThat(
            parentsRecursively(
                "bright white", listOf(
                    LuggageRule("light red", mapOf(Pair("bright white", 1)))
                )
            )
        ).containsExactly("light red")

        assertThat(
            parentsRecursively(
                "bright white", listOf(
                    LuggageRule("light red", mapOf(Pair("bright white", 1))),
                    LuggageRule("muted yellow", mapOf(Pair("bright white", 2)))
                )
            )
        ).containsExactly("light red", "muted yellow")

        assertThat(
            parentsRecursively(
                "bright white", listOf(
                    LuggageRule("light red", mapOf(Pair("bright white", 1))),
                    LuggageRule("muted yellow", mapOf(Pair("light red", 2)))
                )
            )
        ).containsExactly("light red", "muted yellow")
    }

    @Test
    fun childCountsRecursively() {
        assertThat(childCountsRecursively("bag", emptyList())).isEqualTo(0)

        assertThat(
            childCountsRecursively(
                "bag", listOf(
                    LuggageRule("bag", mapOf(Pair("bright white", 1)))
                )
            )
        ).isEqualTo(1)

        assertThat(
            childCountsRecursively(
                "bag", listOf(
                    LuggageRule("bag", mapOf(Pair("bright white", 2)))
                )
            )
        ).isEqualTo(2)

        assertThat(
            childCountsRecursively(
                "bag", listOf(
                    LuggageRule("bag", mapOf(Pair("bright white", 2))),
                    LuggageRule("bag", mapOf(Pair("bright black", 2)))
                )
            )
        ).isEqualTo(4)

        assertThat(
            childCountsRecursively(
                "bag", listOf(
                    LuggageRule("bag", mapOf(Pair("bright white", 1))),
                    LuggageRule("bright white", mapOf(Pair("bright black", 2)))
                )
            )
        ).isEqualTo(3)

        assertThat(
            childCountsRecursively(
                "bag", listOf(
                    LuggageRule("bag", mapOf(Pair("bright white", 3))),
                    LuggageRule("bright white", emptyMap())
                )
            )
        ).isEqualTo(3)

        assertThat(
            childCountsRecursively(
                "bag", listOf(
                    LuggageRule("bag", mapOf(Pair("bright white", 2))),
                    LuggageRule("bright white", mapOf(Pair("bright black", 2))),
                    LuggageRule("bright black", emptyMap())
                )
            )
        ).isEqualTo(6)

        assertThat(
            childCountsRecursively(
                "bag", listOf(
                    LuggageRule("bag", mapOf(Pair("bright white", 1))),
                    LuggageRule("bright white", mapOf(Pair("bright black", 7))),
                    LuggageRule("bright black", emptyMap())
                )
            )
        ).isEqualTo(8)
    }

}