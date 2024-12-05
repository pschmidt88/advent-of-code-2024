fun main() {
    fun part1(input: List<String>) = parseRulesAndUpdates(input).let { (rules, updates) ->
        updates
            .filter { updateLine -> updateLine == updateLine.fixedOrder(rules) }
            .sumOf { validUpdates -> validUpdates[validUpdates.size / 2] }
    }

    fun part2(input: List<String>): Int = parseRulesAndUpdates(input).let { (rules, updates) ->
        updates
            .filterNot { updateLine -> updateLine.isInCorrectOrder(rules) }
            .map { incorrect -> incorrect.fixedOrder(rules) }
            .sumOf { fixed -> fixed[fixed.size / 2] }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

private fun parseRulesAndUpdates(input: List<String>): Pair<Map<Int, List<Int>>, List<List<Int>>> =
    input.splitAt(input.indexOfFirst { it.isBlank() })
        .let {
            it.first.map { ruleLine ->
                ruleLine.split("|").let { split ->
                    Pair(split[0].toInt(), split[1].toInt())
                }
            }.groupBy({ rulePair -> rulePair.first }, { rulePair -> rulePair.second }) to
                    it.second.drop(1).map { updateLine ->
                        updateLine.split(",").map { updateNumber ->
                            updateNumber.toInt()
                        }
                    }
        }

private fun List<Int>.isInCorrectOrder(rules: Map<Int, List<Int>>) = this == this.fixedOrder(rules)

private fun List<Int>.fixedOrder(rules: Map<Int, List<Int>>) = this.sortedWith { o1, o2 ->
    when (rules[o1]?.contains(o2) == true) {
        true -> -1
        false -> 1
    }
}

/**
 * Naive implementation without any checks
 */
private fun <E> List<E>.splitAt(index: Int): Pair<List<E>, List<E>> {
    val left = mutableListOf<E>()
    val right = mutableListOf<E>()

    forEachIndexed { i, value ->
        when (i >= index) {
            false -> left.add(value)
            true -> right.add(value)
        }
    }

    return left.toList() to right.toList()
}
