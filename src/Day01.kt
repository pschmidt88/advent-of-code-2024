import java.util.regex.Pattern
import kotlin.math.absoluteValue

fun main() {

    fun part1(input: List<String>): Int {
        val (left, right) = splitInputToPairOfLists(input).let {
            it.first.sorted() to it.second.sorted()
        }

        return left.mapIndexed { index, value ->
            (value - right[index]).absoluteValue
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val (left, right) = splitInputToPairOfLists(input).let {
            it.first.sorted() to it.second.sorted()
        }

        return left.sumOf { number ->
            number * right.count { it == number }
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun splitInputToPairOfLists(input: List<String>): Pair<List<Int>, List<Int>> {
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    for (line in input) {
        val (first, second) = line.split(Pattern.compile("\\s+"))
        left.add(first.toInt())
        right.add(second.toInt())
    }
    return left to right
}