// https://adventofcode.com/2022/day/6

fun main() {
    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    val grid = input.toString()
        .removeSurrounding("[", "]")
        .split(",")
        .map { it.trim() }
        .map { it.chunked(1) }
        .map { it -> it.map { it.toInt() } }

    println(day08Part1(grid)) // 1560
    val part2 = day08Part2(grid)
    println(part2.maxOfOrNull { it.max() }) // 25200

}

fun heightCheck(index: Int, trees: List<Int>): Boolean {
    var left = true
    var right = true

    if (index == 0 || index == trees.size - 1) {
        return true
    } else {
        for ( i in index-1 downTo 0) {
            if (trees[index] > trees[i]) {
                continue
            } else {
                left = false
                break
            }
        }

        for ( i in index+1 until trees.size) {
            if (trees[index] > trees[i]) {
                continue
            } else {
                right = false
                break
            }
        }
        return left || right
    }
}

fun scenicScore(index: Int, trees: List<Int>): Pair<Int, Int> {
    var left = 0
    var right = 0

    if (index == 0) left++
    if (index == trees.size - 1) right++

    for (i in index-1 downTo 0) {
        if (trees[index] > trees[i]) {
            left++
        } else if (trees[index] == trees[i]) {
            left++
            break
        }
    }
    for (i in index+1 until trees.size) {

        if (trees[index] > trees[i]) {
            right++
        } else if (trees[index] <= trees[i]) {
            right++
            break
        }
    }
    return Pair(left, right)
}

fun getVertical(index: Int, grid: List<List<Int>>): List<Int> {
    val list = mutableListOf<Int>()
    for (i in grid.indices) {
        list.add(grid[i][index])
    }
    return list
}

fun day08Part1(input: List<List<Int>>): Int {
    var count = 0
    input.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { treeIndex, tree ->
            if (rowIndex == 0 ||
                rowIndex == (input.size - 1) ||
                treeIndex == 0 ||
                treeIndex == (row.size - 1)) {
                count ++
            }
            else {
                val col = getVertical(treeIndex, input)
                if (heightCheck(treeIndex, row) || heightCheck(rowIndex, col)) {
                    count++
                }
            }
        }
    }
    return count
}

fun day08Part2(input: List<List<Int>>): List<List<Int>> {
    return input.mapIndexed { rowIndex, row ->
        List(row.size) { treeIndex ->
            val col = getVertical(treeIndex, input)
            val rowScore = scenicScore(treeIndex, row).toList()
            val colScore = scenicScore(rowIndex, col).toList()
            val score = rowScore + colScore
            val newScore = score.filter { it > 0 }
            newScore.fold(1) { acc, i -> acc * i }
        }
    }
}
