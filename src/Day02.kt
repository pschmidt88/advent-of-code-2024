import java.util.regex.Pattern
import kotlin.math.absoluteValue

fun main() {

    fun part1(input: List<String>): Int {
        return input.map { report -> report.split(Pattern.compile("\\s+")).map { it.toInt() } }
            .map { Report(it) }
            .partition { report -> report.isSafe() }
            .first.size
    }

    fun part2(input: List<String>): Int {
         val (safe, unsafe) = input.map { report -> report.split(Pattern.compile("\\s+")).map { it.toInt() } }
            .map { Report(it) }
            .partition { report -> report.isSafeWithProblemDampener() }

        println(unsafe)

        return safe.size
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

data class Report(private val levels: List<Int>) {
    enum class Direction {
        INCREASING, DECREASING
    }

    fun isSafe() = isReportSafe(levels)

    fun isSafeWithProblemDampener(): Boolean {
        if (isSafe()) return true

        val subset = IntRange(0, levels.size-1).map { removeLevel ->
            levels.toMutableList().also { it.removeAt(removeLevel) }
        }

        return subset.any(::isReportSafe)
    }

    private fun isReportSafe(levels: List<Int>): Boolean {
        var reportDirection: Direction? = null
        levels.forEachIndexed { index, level ->
            if (index == 0) {
                return@forEachIndexed
            }

            val diff = levels[index-1] - level

            if (diff.absoluteValue > 3) {
                return false
            }

            val direction = when {
                diff > 0 -> Direction.INCREASING
                diff < 0 -> Direction.DECREASING
                else -> {
                    return false
                }
            }

            if (reportDirection == null) {
                reportDirection = direction
                return@forEachIndexed
            }

            if (reportDirection != direction) {
                return false
            }
        }

        return true
    }
}