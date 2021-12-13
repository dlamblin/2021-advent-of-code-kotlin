fun main() {
    val day = "Day12"

    fun MutableMap<String, Triple<MutableSet<String>, Int, Int>>.connectNodes(node1: String, node2: String) {
        if (containsKey(node1)) {
            this[node1]?.first?.add(node2)
        } else {
            put(node1, Triple(mutableSetOf(node2), 0, if (node1.lowercase() == node1) 1 else Int.MAX_VALUE))
        }
    }

    fun readEdges(input: List<String>): MutableMap<String, Triple<MutableSet<String>, Int, Int>> {
        // node name to its connected nodes set and a minimum visits, and maximum allowed visits
        val edge = mutableMapOf<String, Triple<MutableSet<String>, Int, Int>>()
        for (line in input) {
            val (node1, node2) = line.split('-')
            edge.connectNodes(node1, node2)
            // rev direction
            edge.connectNodes(node2, node1)
        }
        return edge
    }

    fun MutableMap<String, Triple<MutableSet<String>, Int, Int>>.paths(
        from: String, to: String, path: List<String> = emptyList(),
    ): Long {
        val pathHere = listOf(*(path).toTypedArray().plus(from))
        if (from == to) {
            // Confirm minimum visits met
            for ((node, min) in map { (k, v) -> Pair(k, v.second) }.filter { it.second > 0 }) {
                if (pathHere.count { it == node } < min) return 0
            }
            // println(pathHere.joinToString(" - "))
            return 1
        }
        var pathCount = 0L
        for (node in getValue(from).first) {
            if (pathHere.count { it == node } < getValue(node).third) {
                pathCount += paths(node, to, pathHere)
            }
        }
        return pathCount
    }

    fun part1(input: List<String>): Long {
        val edges = readEdges(input)
        // println("$edges")
        return edges.paths("start", "end")
    }

    fun part2(input: List<String>): Long {
        val edges = readEdges(input)
        // println("$edges")
        return edges.keys.filter { it == it.lowercase() && it != "end" }
            .sumOf {
                // println("## $it ##")
                val original = edges[it]!!
                if (it != "start")
                    edges[it] = Triple(original.first, 2, 2)
                val paths = edges.paths("start", "end")
                edges[it] = original
                paths
            }
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 226L)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 3509L)

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}