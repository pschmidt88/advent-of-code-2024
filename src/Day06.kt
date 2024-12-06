import java.util.regex.Pattern

fun main() {

    fun part1(input: List<String>): Int {
        var guardPosition: Pair<Int, Int> = 0 to 0
        val obstaclePositions: MutableList<Pair<Int, Int>> = mutableListOf()

        input.forEachIndexed { x, line ->
            Pattern.compile("(#)|(\\^)").toRegex().findAll(line).forEach {
                when (it.value) {
                    "#" -> obstaclePositions.add(Pair(x, it.range.first))
                    "^" -> guardPosition = Pair(x, it.range.first)
                }
            }
        }

        val map = Map(Pair(input.first().length, input.size), guardPosition, obstaclePositions)

        while (map.guardOnMap()) {
            map.moveGuard()
        }
        // class Map {
        // val dimensions = input.size to input.first().size
        // val guardPosition = Pair(x,y)
        // val visited = setOf(Pair(x,y))
        // val obstaclePositions = List<Pair<x,y>>
        //}

        // while (map.guardOnMap()) {
        //   map.moveGuard()
        // }

        // return map.visitedPositions
        return 0
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

data class Map(val dimension: Pair<Int, Int>,
               val guardPosition: Pair<Int, Int>,
               val obstaclePositions: List<Pair<Int, Int>>
) {
    // TODO: guard direction

    fun guardOnMap(): Boolean {
        return (0 <= guardPosition.first && guardPosition.first <= dimension.first) &&
                (0 <= guardPosition.second && guardPosition.second <= dimension.second)
    }

    fun moveGuard() {
        TODO("move me and add visited fields to visited")
    }

    val visited: MutableSet<Pair<Int, Int>> = mutableSetOf(guardPosition)

}
