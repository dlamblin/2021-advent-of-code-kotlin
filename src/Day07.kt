import java.util.Collections.max
import java.util.Collections.min
import kotlin.math.abs
import kotlin.system.measureNanoTime

fun main() {
    val day = "Day07"

    fun part1(input: List<Int>): Int {
        val min = min(input)
        val max = max(input)
        return min((min..max).map { c -> input.sumOf { abs(it - c) } })
    }

    fun part1Alt(input: List<Int>): Int {
        var (min, max) = Pair(input.first(), input.first())
        input.forEach { min = min.coerceAtMost(it); max = max.coerceAtLeast(it) }
        return min((min..max).map { c -> input.sumOf { abs(it - c) } })
    }

    fun part2(input: List<Int>): Int {
        val min = min(input)
        val max = max(input)
        var cost = 0
        val costMap = (0..max).associateWith { cost += it; cost }
        return min((min..max).map { c -> input.sumOf { costMap[abs(it - c)] ?: 0 } })
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test").toListOfInts().first()
    println("\t##\tPart 1")
    var testResult1: Int
    var timeInNanos = measureNanoTime {
        testResult1 = part1(testInput)
    }
    println("Result:\t\t${testResult1} in $timeInNanos ns")
    timeInNanos = measureNanoTime {
        testResult1 = part1Alt(testInput)
    }
    println("ResultAlt:\t${testResult1} in $timeInNanos ns")
    check(testResult1 == 37)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 168)

    val input = readInput(day).toListOfInts().first()
    println("\t#\tRunning")
    println("\t##\tPart 1")
    timeInNanos = measureNanoTime {
        println("Result:\t\t${part1(input)}")
    }
    println("\t\tin $timeInNanos ns")
    timeInNanos = measureNanoTime {
        println("Result:\t\t${part1Alt(input)}")
    }
    println("\t\tin $timeInNanos ns")
    println("\t##\tPart 2")
    timeInNanos = measureNanoTime {
        println("Result:\t\t${part2(input)}")
    }
    println("\t\t in $timeInNanos ns")
}
