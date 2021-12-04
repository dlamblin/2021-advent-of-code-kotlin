fun main() {
    /**
     * Count when lines increase.
     */
    fun increasedInts(inputInts: List<Int>): Int {
        var increased = 0
        for (pair in inputInts.windowed(2, 1)) {
            increased += if (pair.first() < pair.last()) 1 else 0
        }
        return increased
    }

    fun part1(input: List<String>): Int {
        val inputInts = input.map { it.toInt() }
        return increasedInts(inputInts)
    }

    fun part2(input: List<String>): Int {
        val threeSums = input.map { it.toInt() }.windowed(3, 1).map { it.sum() }
        return increasedInts(threeSums)
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput("Day01_test")
    println("\t#\tPart 1")
    println(part1(testInput))
    check(part1(testInput) == 7)
    println("\t#\tPart 2")
    println(part2(testInput))

    println("\t#\tRunning")
    val input = readInput("Day01")
    println("\t#\tPart 1")
    println(part1(input))
    println("\t#\tPart 2")
    println(part2(input))
}
