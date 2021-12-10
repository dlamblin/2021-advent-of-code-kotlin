fun main() {
    val day = "Day09"

    fun parseDepths(input: List<String>): Array<Array<Char>> {
        val depths = Array(input[0].length) { Array(input.size) { ' ' } }
        for ((y, line) in input.withIndex()) {
            for ((x, d) in line.withIndex()) {
                depths[x][y] = d
            }
        }
        return depths
    }

    fun part1(input: List<String>): Int {
        var totalRisk = 0
        val depths = parseDepths(input)
        for (x in depths.indices) {
            for (y in depths[x].indices) {
                val d = depths[x][y]
                if (d < '9') {
                    if (x > 0 && depths[x-1][y] < d) continue
                    if (x < depths.size-1 && depths[x+1][y] < d) continue
                    if (y > 0 && depths[x][y-1] < d) continue
                    if (y < depths[x].size-1 && depths[x][y+1] < d) continue
                    totalRisk += d.digitToInt()+1
                }
            }
        }
        return totalRisk
    }

    fun flow(depths: Array<Array<Char>>, xIn: Int, yIn: Int): Int {
        var changed = 0
        var loopXIn = xIn
        var x = xIn
        var y = yIn
        do {
            // flow right from start
            while (x < depths.size && depths[x][y] < '9') {
                depths[x][y] = '='
                changed++
                x++
            }
            // flow left from start
            x = loopXIn
            while (--x >= 0 && depths[x][y] < '9') {
                depths[x][y] = '='
                changed++
            }
            // flow up
            while (--y >= 0) {
                for (ux in depths.indices) {
                    if (depths[ux][y+1] == '=' && depths[ux][y] < '9') {
                        depths[ux][y] = '='
                        changed++
                    }
                }
                // flow upper line right
                for (urx in depths.indices.drop(1)) {
                    if (depths[urx-1][y] == '=' && depths[urx][y] < '9') {
                        depths[urx][y] = '='
                        changed++
                    }
                }
                // flow upper line left
                for (ulx in depths.indices.reversed().drop(1)) {
                    if (depths[ulx+1][y] == '=' && depths[ulx][y] < '9') {
                        depths[ulx][y] = '='
                        changed++
                    }
                }
            }
            // flow down
            y = 0
            while (++y < depths[0].size) {
                for (dx in depths.indices) {
                    if (depths[dx][y-1] == '=' && depths[dx][y] < '9') {
                        depths[dx][y] = '='
                        changed++
                    }
                }
                // flow lower line right
                for (drx in depths.indices.drop(1)) {
                    if (depths[drx-1][y] == '=' && depths[drx][y] < '9') {
                        depths[drx][y] = '='
                        changed++
                    }
                }
                // flow lower line left
                for (dlx in depths.indices.reversed().drop(1)) {
                    if (depths[dlx+1][y] == '=' && depths[dlx][y] < '9') {
                        depths[dlx][y] = '='
                        changed++
                    }
                }
            }
            // See if fill down and left-right opened a fill up?
            var brk = false
            for (sx in depths.indices) {
                for (sy in depths[sx].indices) {
                    if (sy+1 == depths[sx].size) continue
                    if (depths[sx][sy] < '9' && depths[sx][sy+1] == '=') {
                        x = sx
                        loopXIn = sx
                        y = sy
                        brk = true
                        break
                    }
                }
                if (brk) break
            }
        } while ( brk )
        return changed
    }

    fun printDepths(depths: Array<Array<Char>>) {
        for (y in depths[0].indices) {
            for (x in depths.indices) {
                print(depths[x][y])
            }
            println()
        }
    }

    fun part2(input: List<String>): Int {
        val largest = mutableListOf(0, 0, 0)
        val depths = parseDepths(input)
        var basins = 0
        // find each possible basin start
        for (x in depths.indices) {
            for (y in depths[x].indices) {
                if (depths[x][y] < '9') {
                    // Start a fill here, keep track of 3 largest sizes
                    largest.add(flow(depths, x, y)) // now 4 long
                    println("Basin ${++basins} size: ${largest.last()}")
                    val min = largest.minOf { it }
                    // remove the minimum value making it 3 long again
                    largest.removeAt(largest.withIndex().first { it.value == min }.index)
                    // clean up this basin's '=' to '9's
                    for (cx in depths.indices) {
                        for (cy in depths[cx].indices) {
                            if (depths[cx][cy] == '=') {
                                depths[cx][cy] = '9'
                            }
                        }
                    }
                }
            }
        }
        println("Top 3 basin sizes were: $largest")
        return largest[0] * largest[1] * largest[2]
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 15)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 1134)

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
