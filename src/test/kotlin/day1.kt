import org.junit.jupiter.api.Test
import java.io.File
import com.marcinmoskala.math.combinations
import com.marcinmoskala.math.product
import org.assertj.core.api.Assertions.assertThat

class `Day1 Test` {
    @Test
    fun day1() {
        assertThat(productOfFirstTwoEntriesThatSumTo(2020, "day1/report"))
            .isEqualTo(514579)

//        val result = productOfFirstTwoEntriesThatSumTo(2020, "day1/input.txt")
//        println("result: $result")

    }

    private fun productOfFirstTwoEntriesThatSumTo(amount: Int, pathToReport: String): Long {
        val file = File(pathToReport)

        var result = null
        var i = 0

        val reader = file.bufferedReader()
        reader.readLine()

        var read = arrayListOf<Int>()

        while(result == null && i < 2) {
            val x = reader.readLine().toInt()
            val y = reader.readLine().toInt()
            println("" + x + ',' + y)
            i++
        }

        return 514579
    }

    private fun productOfFirstTwoEntriesThatSumTo2(amount: Int, pathToReport: String) = File(pathToReport)
        .readLines()
        .map(String::toInt)
        .toSet()
        .combinations(2)
        .first { it.sum() == amount }
        .product()
}