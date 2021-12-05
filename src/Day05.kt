import java.lang.Integer.min

fun main() {
    val day = "Day05"

    fun printMap(m: Array<Array<Int>>, xMax: Int, yMax: Int) {
        for (y in 0..yMax) {
            for (x in 0..xMax) {
                print(
                    when (m[x][y]) {
                        0 -> '.'
                        in 1..9 -> m[x][y]
                        else -> 'X'
                    }
                )
            }
            println("")
        }
    }

    fun part1(input: List<String>, showMap: Boolean = false): Int {
        var xMax = 0
        var yMax = 0
        var hotSpots = 0
        val m = Array(1000) { Array(1000) { 0 } }
        for (line in input) {
            // like x1,y1 -> x2,y2
            val (p1, p2) = line.split(" -> ")
            val (x1, y1) = p1.split(",")
            val (x2, y2) = p2.split(",")
            val j1 = x1.toInt()
            val k1 = y1.toInt()
            val j2 = x2.toInt()
            val k2 = y2.toInt()
            xMax = xMax.coerceAtLeast(j1).coerceAtLeast(j2)
            yMax = yMax.coerceAtLeast(k1).coerceAtLeast(k2)
            if (j1 == j2) {
                for (i in min(k1, k2)..k1.coerceAtLeast(k2)) {
                    m[j1][i] += 1
                    if (m[j1][i] == 2) {
                        hotSpots++
                    }
                }
            } else if (k1 == k2) {
                for (i in min(j1, j2)..j1.coerceAtLeast(j2)) {
                    m[i][k1] += 1
                    if (m[i][k1] == 2) {
                        hotSpots++
                    }
                }
            }
        }
        if (showMap) printMap(m, xMax, yMax)
        return hotSpots
    }

    fun part2(input: List<String>, showMap: Boolean = false): Int {
        var xMax = 0
        var yMax = 0
        var hotSpots = 0
        val m = Array(1000) { Array(1000) { 0 } }
        for (line in input) {
            // like x1,y1 -> x2,y2
            val (p1, p2) = line.split(" -> ")
            val (x1, y1) = p1.split(",")
            val (x2, y2) = p2.split(",")
            var j1 = x1.toInt()
            var k1 = y1.toInt()
            val j2 = x2.toInt()
            val k2 = y2.toInt()
            xMax = xMax.coerceAtLeast(j1).coerceAtLeast(j2)
            yMax = yMax.coerceAtLeast(k1).coerceAtLeast(k2)
            val xIt = j2.compareTo(j1)
            val yIt = k2.compareTo(k1)
            while (j1 != j2 || k1 != k2) {
                m[j1][k1] += 1
                if (m[j1][k1] == 2) {
                    hotSpots++
                }
                j1 += xIt
                k1 += yIt
            }
            m[j1][k1] += 1
            if (m[j1][k1] == 2) {
                hotSpots++
            }
        }
        if (showMap) printMap(m, xMax, yMax)
        return hotSpots
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t##\tPart 1")
    val testResult1 = part1(testInput, true)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 5)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput, true)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 12)

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input, false)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input, false)}")
}
