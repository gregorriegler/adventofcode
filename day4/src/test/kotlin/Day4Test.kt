import Field.*
import org.assertj.core.api.Assertions.assertThat
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
        assertThat(scanPassportData("ecl:gry")).isEqualTo(PassportData(Pair(ecl, "gry")))
        assertThat(scanPassportData("byr:1920")).isEqualTo(PassportData(Pair(byr, "1920")))
        assertThat(scanPassportData("ecl:gry byr:1920")).isEqualTo(PassportData(Pair(ecl, "gry"), Pair(byr, "1920")))
        assertThat(scanPassportData("ecl:gry\nbyr:1920")).isEqualTo(PassportData(Pair(ecl, "gry"), Pair(byr, "1920")))
    }

    @Test
    fun validatePassportData() {
        assertThat(valid(PassportData(Pair(ecl, "gry")))).isFalse
        assertThat(valid(PassportData(Pair(ecl, "gry"), Pair(byr, "1920")))).isFalse
        assertThat(
            valid(
                PassportData(
                    Pair(byr, "1920"),
                    Pair(iyr, "2010"),
                    Pair(eyr, "2020"),
                    Pair(hgt, "150cm"),
                    Pair(hcl, "#ffffff"),
                    Pair(ecl, "amb"),
                    Pair(pid, "1"),
                    Pair(cid, "1"),
                )
            )
        ).isTrue
        assertThat( // cid optional
            valid(
                PassportData(
                    Pair(byr, "1920"),
                    Pair(iyr, "2010"),
                    Pair(eyr, "2020"),
                    Pair(hgt, "150cm"),
                    Pair(hcl, "#ffffff"),
                    Pair(ecl, "amb"),
                    Pair(pid, "1")
                )
            )
        ).isTrue
    }

    @Test
    fun `validate byr`() {
        assertThat(byr.valid(null)).isFalse
        assertThat(byr.valid("1")).isFalse
        assertThat(byr.valid("1919")).isFalse
        assertThat(byr.valid("1920")).isTrue
        assertThat(byr.valid("2000")).isTrue
        assertThat(byr.valid("2003")).isFalse
    }

    @Test
    fun `validate iyr`() {
        assertThat(iyr.valid(null)).isFalse
        assertThat(iyr.valid("1")).isFalse
        assertThat(iyr.valid("2009")).isFalse
        assertThat(iyr.valid("2010")).isTrue
        assertThat(iyr.valid("2020")).isTrue
        assertThat(iyr.valid("2021")).isFalse
    }

    @Test
    fun `validate eyr`() {
        assertThat(eyr.valid(null)).isFalse
        assertThat(eyr.valid("1")).isFalse
        assertThat(eyr.valid("2019")).isFalse
        assertThat(eyr.valid("2020")).isTrue
        assertThat(eyr.valid("2030")).isTrue
        assertThat(eyr.valid("2031")).isFalse
    }

    @Test
    fun `validate hgt`() {
        assertThat(hgt.valid(null)).isFalse
        assertThat(hgt.valid("149cm")).isFalse
        assertThat(hgt.valid("150cm")).isTrue
        assertThat(hgt.valid("193cm")).isTrue
        assertThat(hgt.valid("194cm")).isFalse
        assertThat(hgt.valid("58in")).isFalse
        assertThat(hgt.valid("59in")).isTrue
        assertThat(hgt.valid("76in")).isTrue
        assertThat(hgt.valid("77in")).isFalse
        assertThat(hgt.valid("77mm")).isFalse
    }

    @Test
    fun `validate hcl`() {
        assertThat(hcl.valid(null)).isFalse
        assertThat(hcl.valid("123")).isFalse
        assertThat(hcl.valid("#12345")).isFalse
        assertThat(hcl.valid("#123456")).isTrue
        assertThat(hcl.valid("#abcdef")).isTrue
        assertThat(hcl.valid("#a2c5ef")).isTrue
    }

    @Test
    fun `validate ecl`() {
        assertThat(ecl.valid(null)).isFalse
        assertThat(ecl.valid("123")).isFalse
        assertThat(ecl.valid("ama")).isFalse
        assertThat(ecl.valid("amb")).isTrue
        assertThat(ecl.valid("blu")).isTrue
    }
}


