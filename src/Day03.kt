import java.util.regex.Pattern

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)").toRegex()
                .findAll(line)
                .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
                .sum()
        }
    }

    fun part2(input: List<String>): Int {
        TODO("Not working")
        return input.sumOf { line ->
            Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)").toRegex()
                .findAll(line)
                .onEach { println(it.groupValues) }
                .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
                .sum()
        }
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
//    check(part2(testInput) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
