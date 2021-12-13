// Stack methods
typealias Stack<T> = MutableList<T>
fun <T> Stack<T>.push(item: T) = add(item)
fun <T> Stack<T>.pop(): T? = removeLastOrNull()
fun <T> Stack<T>.peek(): T? = lastOrNull()

fun main() {
    val day = "Day10"
    val score = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )
    val score2 = mapOf(
        '(' to 1,
        '[' to 2,
        '{' to 3,
        '<' to 4,
    )
    val open = arrayOf('(','[','{','<')
    val close = arrayOf(')',']','}','>')
    val match = mapOf(
        *(open zip close).toTypedArray(),
        *(close zip open).toTypedArray())
    fun part1(input: List<String>): Int {
        var tot = 0
        for (line in input) {
            val stack:Stack<Char> = MutableList(0){' '}
            for (c in line) {
                if (c in open) {
                    stack.push(c)
                } else {
                    val (i, _) = close.withIndex()
                        .find { it.value == c }!!
                    if (stack.peek() != open[i]) {
                        tot += score[c]!!
                        break
                    } else {
                        stack.pop()
                    }
                }
            }
        }
        return tot
    }

    fun part2(input: List<String>): Long {
        val scores = mutableListOf<Long>()
        for (line in input) {
            val stack:Stack<Char> = MutableList(0){' '}
            var tot = 0L
            var brk = false
            for (c in line) {
                // println("$c to ${stack.joinToString("")}")
                if (c in open) {
                    stack.push(c)
                } else {
                    val (i, _) = close.withIndex()
                        .find { it.value == c }!!
                    if (stack.peek() != open[i]) {
                        brk = true
                        break
                    } else {
                        stack.pop()
                    }
                }
            }
            if (!brk) {
                print("${stack.joinToString("")} scores ")
                while (stack.size > 0) {
                    tot *= 5
                    tot += score2[stack.pop()]!!
                }
                println(tot)
                scores.add(tot)
            }
        }
        scores.sort()
        return scores[scores.size/2]
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test")
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 26397)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 288957L)

    val input = readInput(day)
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
