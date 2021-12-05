fun main() {
    val day = "Day02"

    fun part1(input: List<String>): Int {
        var hor = 0
        var dep = 0
        val dirDists = input.map { it.split(" ", ignoreCase = false, limit = 2) }
        for (dirDist in dirDists) {
            val i = dirDist.last().toInt()
            when (dirDist.first()) {
                "forward" -> hor += i
                "down" -> dep += i
                "up" -> dep -= i
            }
        }
        println("Horizontal: ${hor}, Depth: ${dep}")
        return hor * dep
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var hor = 0
        var dep = 0
        val dirDists = input.map { it.split(" ", ignoreCase = false, limit = 2) }
        for (dirDist in dirDists) {
            val i = dirDist.last().toInt()
            when (dirDist.first()) {
                "forward" -> { hor += i; dep += aim * i }
                "down" -> aim += i
                "up" -> aim -= i
            }
        }
        println("Horizontal: ${hor}, Depth: ${dep}")
        return hor * dep
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t#\tPart 1")
    check(part1(testInput) == 150)
    println("\t#\tPart 2")
    println(part2(testInput))

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t#\tPart 1")
    println(part1(input))
    println("\t#\tPart 2")
    println(part2(input))
}
