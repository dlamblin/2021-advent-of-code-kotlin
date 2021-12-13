fun main() {
    val day = "Day13"

    fun parseInput(input: List<String>): Pair<List<Pair<Int, Int>>, List<Pair<String, Int>>> {
        var points = input.filter { it.contains(',') }
            .map {
                val (x, y) = it.split(',')
                Pair(x.toInt(), y.toInt())
            }
        val folds = input.filter { it.startsWith("fold") }
            .map {
                val (xy, v) = it.substring(11).split('=')
                Pair(xy, v.toInt())
            }
        return Pair(points, folds)
    }

    fun doFold(
        it: Pair<String, Int>,
        points: List<Pair<Int, Int>>
    ): List<Pair<Int, Int>> {
        val (XorY, v) = it
        return points.filter { (x, y) ->
            (
                    (XorY == "x" && x != v)
                            || (XorY == "y" && y != v))
        }
            .map { (x, y) ->
                if (XorY == "x" && x > v) {
                    Pair(v - (x - v), y)
                } else if (XorY == "y" && y > v) {
                    Pair(x, v - (y - v))
                } else {
                    Pair(x, y)
                }
            }
    }

    fun part1(input: List<String>): Int {
        val pair = parseInput(input)
        var points = pair.first
        val folds = pair.second
        folds.dropLast(folds.size-1).forEach {
            points = doFold(it, points)
        }
        return points.toSet().size
    }

    fun part2(input: List<String>): Int {
        val pair = parseInput(input)
        var points = pair.first
        val folds = pair.second
        folds.forEach {
            points = doFold(it, points)
        }
        val maxX = points.maxOf { it.first }
        val maxY = points.maxOf { it.second }
        println("$maxX $maxY")
        val msg = Array(maxY+1){ Array(maxX+1){ ' ' } }
        points.forEach { (x,y)->msg[y][x]='#'}
        for (mline in msg) {
            println(mline.joinToString(""))
        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 17)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 0)

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
