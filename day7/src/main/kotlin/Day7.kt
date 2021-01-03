import java.io.File

fun main() {
    println(day7(File("day7/input").readText()))
}

fun day7(input: String): Int {
    val rules = input.lines()
        .map { it.toRule() }

    val result = rules.filter { it.hasContainingBag("shiny gold") }
        .flatMap { parentsRecursively(it.bag, rules) }
        .count()

    return result
}

fun parentsRecursively(bag: String, rules: List<LuggageRule>): List<String> {
    TODO("Not yet implemented")
}

// this should be a regex xD
fun String.toRule() = this.split(" bags contain ").let {
    LuggageRule(
        it[0],
        if (it[1].substringBefore(' ') == "no") emptyMap()
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

data class LuggageRule(
    val bag: String,
    val containingBags: Map<String, Int>
) {
    fun hasContainingBag(bag: String): Boolean {
        return false
    }
}

