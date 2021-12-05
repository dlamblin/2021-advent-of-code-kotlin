fun main() {
    val day = "Day00"

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t#\tPart 1")
    check(part1(testInput) == 1)
    println("\t#\tPart 2")
    println(part2(testInput))

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t#\tPart 1")
    println(part1(input))
    println("\t#\tPart 2")
    println(part2(input))
}
