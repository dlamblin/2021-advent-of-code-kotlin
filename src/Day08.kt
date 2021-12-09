private val text = Regex("[^a-z]+")

fun main() {
    val day = "Day08"

    fun part1(input: List<String>): Int {
        return input.map {
            it.split(" | ")[1]
                .split(text)
        }.flatten().count {
            it.length in 2..4 || it.length == 7
        }
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for ((digits, display) in input
            .map { it.split(" | ") }
            .map { line -> Pair(
                line[0].split(text).map{ it.toSet() },
                line[1].split(text).map{ it.toSet() },
            )}
        ) {
            val (one, seven, four, eight) = arrayOf(
                digits.first { it.size == 2 },
                digits.first { it.size == 3 },
                digits.first { it.size == 4 },
                digits.first { it.size == 7 })
            val sixLit = digits.filter { it.size == 6 }
            val nine = sixLit.first { it.containsAll(four) }
            val zero = sixLit.first { it != nine && it.containsAll(one) }
            val six = sixLit.first { it != nine && it != zero }
            val fiveLit = digits.filter { it.size == 5 }
            val three = fiveLit.first { it.containsAll(one) }
            val five = fiveLit.first { it != three && it.minus(four).size == 2 }
            val two = fiveLit.first { it != three && it != five }
            val valOf = mapOf(
                zero to 0, one to 1, two to 2, three to 3, four to 4,
                five to 5, six to 6, seven to 7, eight to 8, nine to 9,
            )
            result += valOf.getValue(display[0]) * 1000 +
                    valOf.getValue(display[1]) * 100 +
                    valOf.getValue(display[2]) * 10 +
                    valOf.getValue(display[3])
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 26)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 61229)

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
