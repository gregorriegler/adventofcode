import Field.byr
import Field.ecl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class Day4Test {

    private val passports = """
        ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
        byr:1937 iyr:2017 cid:147 hgt:183cm

        iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
        hcl:#cfa07d byr:1929

        hcl:#ae17e1 iyr:2013
        eyr:2024
        ecl:brn pid:760753108 byr:1931
        hgt:179cm

        hcl:#cfa07d eyr:2025 pid:166559648
        iyr:2011 ecl:brn hgt:59in
    """.trimIndent()

    @Disabled
    @Test
    fun acceptance() {
        val result: Int = day4(passports)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun splitByEmptyLine() {
        assertThat(splitByEmptyLine("")).isEmpty()
        assertThat(splitByEmptyLine("a")).isEqualTo(listOf("a"))
        assertThat(splitByEmptyLine("a b")).isEqualTo(listOf("a b"))
        assertThat(splitByEmptyLine("a\nb")).isEqualTo(listOf("a\nb"))
        assertThat(splitByEmptyLine(" a\nb ")).isEqualTo(listOf("a\nb"))
        assertThat(splitByEmptyLine("a\n\nb")).isEqualTo(listOf("a", "b"))
        assertThat(splitByEmptyLine("a\n\nb\n")).isEqualTo(listOf("a", "b"))
        assertThat(splitByEmptyLine("a\n \nb\n")).isEqualTo(listOf("a", "b"))
        assertThat(splitByEmptyLine("a\n \nb c\n")).isEqualTo(listOf("a", "b c"))
        assertThat(splitByEmptyLine("a\n \nb c\n\nd")).isEqualTo(listOf("a", "b c", "d"))
    }

    @Test
    fun scanPassportData() {
        assertThat(scanPassportData("ecl:gry").value).isEqualTo(mapOf(Pair(ecl, "gry")))
        assertThat(scanPassportData("byr:val").value).isEqualTo(mapOf(Pair(byr, "val")))
        assertThat(scanPassportData("ecl:gry byr:val").value).isEqualTo(mapOf(Pair(ecl, "gry"), Pair(byr, "val")))
        assertThat(scanPassportData("ecl:gry\nbyr:val").value).isEqualTo(mapOf(Pair(ecl, "gry"), Pair(byr, "val")))
    }


}

fun day4(input: String): Int {
    return splitByEmptyLine(input)
        .map { scanPassportData(it) }
        .filter { valid(it) }
        .count()
}

fun splitByEmptyLine(input: String): List<String> = input.split(Regex("\n[\\s]*\n"))
    .map { it.trim() }
    .filter { it.isNotEmpty() }

fun scanPassportData(input: String): PassportData {
    return input.split(Regex("[\\s]+"))
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .map { parsePair(it) }
        .toMap()
        .let { PassportData(it) }
}

fun parsePair(input: String): Pair<Field, String> {
    return input.split(":")
        .map { it.trim() }
        .let { Pair(Field.valueOf(it[0]), it[1]) }
}

fun valid(passport: PassportData): Boolean = true


class PassportData(
    val value: Map<Field, String>
)

enum class Field { byr, iyr, eyr, hgt, hcl, ecl, pid, cid }
