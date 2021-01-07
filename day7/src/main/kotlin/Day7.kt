import java.io.File

fun main() {
    println(day7(File("day7/input").readText()))
    println(day7part2(File("day7/input").readText()))
}

fun day7(input: String): Int =
    parentsRecursively(
        "shiny gold",
        input.lines().map { it.toRule() }
    ).count()

fun day7part2(input: String): Int =
    childCountsRecursively(
        "shiny gold",
        input.lines().map { it.toRule() }
    )

fun parentsRecursively(bag: String, rules: List<LuggageRule>): Set<String> {
    val parents = rules.filter { it.hasContainingBag(bag) }.map { it.bag }.toSet()
    return parents + parents.flatMap { parentsRecursively(it, rules) }
}

fun childCountsRecursively(bag: String, rules: List<LuggageRule>): Int =
    rules.filter { it.bag == bag }
        .flatMap { it.containingBags.entries }
        .sumBy { it.value + it.value * childCountsRecursively(it.key, rules) }

fun String.toRule() = LuggageRule.of(this)

data class LuggageRule(
    val bag: String,
    val containingBags: Map<String, Int>
) {
    companion object {

        // this should be a regex xD
        fun of(input: String): LuggageRule = input.split(" bags contain ").let {
            LuggageRule(
                it[0],
                if (it[1].substringBefore(' ') == "no")
                    emptyMap()
                else
                    it[1].split(", ")
                        .map { containing ->
                            Pair(
                                containing.split(' ').drop(1).take(2).joinToString(" "),
                                containing.substringBefore(' ').toInt()
                            )
                        }.toMap()
            )
        }
    }

    fun hasContainingBag(bag: String): Boolean = containingBags.containsKey(bag)
}

