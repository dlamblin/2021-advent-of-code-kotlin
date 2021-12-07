import java.util.Collections.max
import java.util.Collections.min
import kotlin.math.abs

fun main() {
    val day = "Day07"

    fun part1(input: List<List<Int>>): Int {
        val min = min(input.first())
        val max = max(input.first())
        return min((min..max).map {
                col -> input.first().map {
            kotlin.math.abs(it - col)
                }.sum() })
    }

    fun part2(input: List<List<Int>>): Int {
        val min = min(input.first())
        val max = max(input.first())
        var cost: Int = 0
        val costMap = (0..max).associateWith { k -> cost += k; cost }
        //costMap.forEach{ (k, v) -> println("$k -> $v") }
        return min((min..max).map {
                col -> input.first().sumOf { costMap[abs(it - col)] ?: 0 }
        })

    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test").toListOfInts()
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 37)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 168)

    val input = readInput(day).toListOfInts()
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
