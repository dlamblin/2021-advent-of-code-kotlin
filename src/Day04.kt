import kotlin.collections.ArrayList

data class Board(val which: Int = 0) {
    val marked = Array(5) { Array(5) { false } }
    var isWon = false
    val colCountX = Array(5) {0}
    val rowCountY = Array(5) {0}
    val numberPlace = mutableMapOf<Int, Pair<Int, Int>>()
    val placeNumber = mutableMapOf<Pair<Int, Int>, Int>()

    fun fill(input: List<List<Int>>): Board {
        for ((y, row) in input.withIndex()) {
            for ((x, num) in row.withIndex()) {
                numberPlace[num] = Pair(x, y)
                placeNumber[Pair(x, y)] = num
            }
        }
        return this
    }

    /**
     * @return whether this mark won the board.
     */
    fun mark(num: Int): Boolean {
        if (numberPlace.contains(num)) {
            val (x, y) = numberPlace[num] ?: (0 to 0)
            marked[x][y] = true
            if (++colCountX[x] > 4) return won()
            if (++rowCountY[y] > 4) return won()
            return false
        }
        return false
    }

    fun sumOfUnmarked(): Int {
        var result = 0
        for (x in 0..4) {
            for (y in 0..4) {
                if (!marked[x][y]) {
                    result += placeNumber[Pair(x, y)] ?: 0
                }
            }
        }
        println("Board #$which has unmarked sum: $result")
        return result
    }

    private fun won(): Boolean {
        println("Board $which won!")
        drawBoard()
        isWon = true
        return true
    }

    private fun drawBoard() {
        println("Board #$which:")
        for (y in 0 .. 4 ) {
            for (x in 0..4) {
                val num = placeNumber[Pair(x, y)] ?: 0
                if (marked[x][y]) {
                    print(if (num < 10) "* $num" else "*$num")
                } else {
                    print(if (num < 10) "  $num" else " $num")
                }
            }
            println()
        }
    }
}

fun main() {
    val day = "Day04"

    fun theSetup(input: List<List<Int>>): Pair<List<Int>, ArrayList<Board>> {
        val picks = input.first()
        val boards = ArrayList<Board>((input.size - 1) / 6)
        var board = 0
        while (board * 6 + 6 < input.size) {
            boards.add(
                Board(board).fill(
                    input.slice(board * 6 + 2..board * 6 + 6)
                )
            )
            board++
        }
        return Pair(picks, boards)
    }

    fun part1(input: List<List<Int>>): Int {
        val (picks, boards) = theSetup(input)
        val winningPick = picks.first { pick ->
            boards.firstOrNull { it.mark(pick) } != null }
        val winningBoard = boards.first(Board::isWon)
        println("Winning Pick was $winningPick")
        return winningPick * winningBoard.sumOfUnmarked()
    }

    fun part2(input: List<List<Int>>): Int {
        val (picks, boards) = theSetup(input)
        var winningBoard = boards.first()
        var boardsLeft = boards.size
        val winningPick = picks.first { pick ->
            boards.forEach {
                if (!it.isWon) {
                    if (it.mark(pick)) {
                        boardsLeft--
                        winningBoard = it
                    }
                }
            }
            boardsLeft == 0
        }
        println("Last winning pick was $winningPick")
        return winningPick * winningBoard.sumOfUnmarked()
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    println("\t#\tTesting")
    val testInput = readInput(day + "_test").toListOfInts()
    println("\t##\tPart 1")
    val testResult1 = part1(testInput)
    println("Result:\t\t${testResult1}")
    check(testResult1 == 4512)
    println("\t##\tPart 2")
    val testResult2 = part2(testInput)
    println("Result:\t\t${testResult2}")
    check(testResult2 == 1924)

    val input = readInput(day).toListOfInts()
    println("\t#\tRunning")
    println("\t##\tPart 1")
    println("Result:\t\t${part1(input)}")
    println("\t##\tPart 2")
    println("Result:\t\t${part2(input)}")
}
