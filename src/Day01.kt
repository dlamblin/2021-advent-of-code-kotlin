fun main() {
    /**
     * Count when lines increase.
     */
    fun List<Int>.countIncInts(): Int = windowed(2, 1)
        .count { it[0] < it[1] }

    fun List<String>.toInts(): List<Int> = map { it.toInt() }

    fun part1(input: List<String>): Int = input.toInts()
        .countIncInts()

    fun part2Orig(input: List<String>): Int = input.toInts()
        .windowed(3, 1)
        .map { it.sum() }
        .countIncInts()

    fun part2(input: List<String>): Int {
        val ints = input.toInts()
        return ints.withIndex().drop(3).count { (i, v) -> ints[i - 3] < v }
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput("Day01_test")
    println("\t#\tPart 1")
    val part1result = part1(testInput)
    println(part1result)
    check(part1result == 7)
    println("\t#\tPart 2")
    val part2result = part2(testInput)
    println(part2result)
    check(part2result == 5)


    println("\t#\tRunning")
    val input = readInput("Day01")
    println("\t#\tPart 1")
    println(part1(input))
    println("\t#\tPart 2")
    println(part2(input))
}
