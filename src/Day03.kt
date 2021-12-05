fun main() {
    val day = "Day03"

    fun part1(input: List<String>): Int {
        val bitLen = input[0].length
        val counts = IntArray(bitLen)
        for (line in input) {
            var i = 0
            for (c in line) {
                when (c) {
                    '1' -> counts[i] += 1
                }
                i++
            }
        }
        println("1s counts: ${counts.joinToString(", ")}")
        val half = input.size / 2
        var bit = 1
        var gamma = 0
        for (i in counts.indices.reversed()) {
            gamma = if (counts[i] > half) (gamma or bit) else gamma
            bit = bit shl 1
        }
        val epsilon = gamma.inv() and (bit - 1)
        println("Gamma: $gamma, Epsilon: $epsilon")
        return gamma * epsilon
    }

    /**
     * Count number of '1' in char position at pos of each String in input List<String>.
     *
     * @return the counted number
     */
    fun charCount(input: List<String>, pos: Int): Int {
        var result = 0
        for (line in input) {
            result += if ('1' == line[pos]) 1 else 0
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var remain = input
        var pos = 0
        while (1 < remain.size) {
            val count = charCount(remain, pos)
            val keep = if (count < remain.size/2.0) '0' else '1'
            remain = remain.filter { keep == it[pos] }
            pos++
        }
        val oxygen = remain.first().toInt(2)
        remain = input
        pos = 0
        while (1 < remain.size) {
            val count = charCount(remain, pos)
            val keep = if (count < remain.size/2.0) '1' else '0'
            remain = remain.filter { keep == it[pos] }
            pos++
        }
        val co2 = remain.first().toInt(2)
        println("Oxygen Generator: $oxygen, CO2 Scrubber: $co2")
        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 198)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 230)

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
