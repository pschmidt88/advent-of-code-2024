import java.util.regex.Pattern

fun main() {

    fun part1(input: List<String>): Int {
        var guardPosition: Pair<Int, Int> = 0 to 0
        val obstaclePositions: MutableList<Pair<Int, Int>> = mutableListOf()

        input.forEachIndexed { y, line ->
            Pattern.compile("(#)|(\\^)").toRegex().findAll(line).forEach {
                when (it.value) {
                    "#" -> obstaclePositions.add(it.range.first to y)
                    "^" -> guardPosition = it.range.first to y
                }
            }
        }

        val game = Game(Pair(input.first().length-1, input.size-1), guardPosition, obstaclePositions)

        while (game.guardOnMap()) {
            game.moveGuard()
        }

        return game.visited.size.also { it.println() }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
//    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

data class Game(val dimension: Pair<Int, Int>,
                val startPosition: Pair<Int, Int>,
                val obstaclePositions: List<Pair<Int, Int>>
) {
    private enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private var guardPosition = startPosition
    private var direction: Direction = Direction.UP

    fun guardOnMap(): Boolean {
        return (0 <= guardPosition.first && guardPosition.first <= dimension.first) &&
                (0 <= guardPosition.second && guardPosition.second <= dimension.second)
    }

    fun moveGuard() {
        visited.add(guardPosition)

        // check if move in direction is possible
        val nextPosition = when (direction) {
            Direction.UP -> guardPosition.first to guardPosition.second - 1
            Direction.DOWN -> guardPosition.first to guardPosition.second + 1
            Direction.LEFT -> guardPosition.first - 1 to guardPosition.second
            Direction.RIGHT -> guardPosition.first + 1 to guardPosition.second
        }

        if (obstaclePositions.contains(nextPosition)) {
            direction = when (direction) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }
            return
        }

        guardPosition = nextPosition
    }

    val visited: MutableSet<Pair<Int, Int>> = mutableSetOf(guardPosition)
}
