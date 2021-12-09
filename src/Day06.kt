fun main() {
    val day = "Day06"

    fun runGenerations(fishAtAge: Array<Long>, days: Long): Long {
        for (i in 1..days) {
            val zeros = fishAtAge[0]
            for (j in 0..5){
                fishAtAge[j] = fishAtAge[j+1]
            }
            fishAtAge[6] = fishAtAge[7] + zeros
            fishAtAge[7] = fishAtAge[8]
            fishAtAge[8] = zeros
        }
        return fishAtAge.sum()
    }

    fun fishGroupedByAge(input: List<List<Int>>): Array<Long> {
        val fishGroupedByAge = Array(9) { 0L }
        input
            .first()
            .groupBy { it }
            .forEach { (k, v) ->
                fishGroupedByAge[k] = v.size.toLong()
            }
        return fishGroupedByAge
    }

    fun part1(input: List<List<Int>>): Long {
        return runGenerations(fishGroupedByAge(input), 80)
    }

    fun part2(input: List<List<Int>>): Long {
        return runGenerations(fishGroupedByAge(input), 256)
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test").toListOfInts()
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 5934L)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 26984457539L)

    val input = readInput(day).toListOfInts()
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
