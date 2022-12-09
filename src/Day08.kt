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
    grid.forEach { println(it) }

    val part2 = day08Part2(grid)
    part2.forEach { println(it) }
    println(part2.maxOfOrNull { it.max() }) //    println(largestPerRow)

}

fun heightCheck(index: Int, trees: List<Int>, direction: String): Boolean {
    var a = ""
    var b = ""
    var left = true
    var right = true

    if (index == 0 || index == trees.size - 1) {
        println("${trees[index]} is an edge")
        return true
    } else {
        for ( i in index-1 downTo 0) {
            if (trees[index] > trees[i]) {
                println("${trees[index]} is greater than ${trees[i]}")
                continue
            } else {
                println("${trees[index]} is less than ${trees[i]}")
                left = false
                break
            }
        }

        for ( i in index+1 until trees.size) {
            if (trees[index] > trees[i]) {
                println("${trees[index]} is greater than ${trees[i]}")
                continue
            } else {
                println("${trees[index]} is less than ${trees[i]}")
                right = false
                break
            }
        }
        when (direction) {
            "row" -> {
                a = "LEFT"
                b = "RIGHT"
            }
            "col" -> {
                a = "UP"
                b = "DOWN"

            }            }
        println("$a: $left - $b: $right")
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
            println("${trees[index]} is greater than ${trees[i]}")
            left++
        } else if (trees[index] == trees[i]) {
            println("${trees[index]} is equals ${trees[i]}")
            left++
            break
        }
    }
    for (i in index+1 until trees.size) {

        if (trees[index] > trees[i]) {
            println("${trees[index]} is greater than ${trees[i]}")
            right++
        } else if (trees[index] <= trees[i]) {
            println("${trees[index]} is equals ${trees[i]}")
            right++
            break
        }
    }
    return Pair(left, right)
}

//fun checkSurrounding(index: String, surroundingTrees: Map<String, List<Int>>): Boolean {
//    if (surroundingTrees.values.contains(emptyList())) {
//        return true
//    } else {
//        when (surroundingTrees.keys) {
//            "up", "down" ->
//        }
//    }
//   return true
//}

enum class Direction {
    Vertical,
    Horizontal
}

fun getVertical(index: Int, grid: List<List<Int>>): List<Int> {
    val list = mutableListOf<Int>()
    for (i in grid.indices) {
        list.add(grid[i][index])
    }
    return list
}

fun split(index: Int, row: List<Int>, direction: Direction): Map<String, List<Int>> {
    val left = mutableListOf<Int>()
    val right = row.takeLast((row.size - 1) - index)
    val split = mutableMapOf<String, List<Int>>()
    for (i in index-1 downTo 0) {
        left.add(row[i])
    }
    when (direction) {
        Direction.Vertical -> {
            split["left"] = left.toList()
            split["right"] = right
        }
        Direction.Horizontal -> {
            split["up"] = left.toList()
            split["down"] = right
        }
    }
    return split
}

fun day08Part1(input: List<List<Int>>): Int {
    var count = 0
    input.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { treeIndex, tree ->
            println("row $rowIndex $row tree $tree")
            if (rowIndex == 0 ||
                rowIndex == (input.size - 1) ||
                treeIndex == 0 ||
                treeIndex == (row.size - 1)) {
                println("$tree is an edge")
                count ++
            }
            else {
                val col = getVertical(treeIndex, input)
                println("col $treeIndex $col tree $tree")
                if (heightCheck(treeIndex, row, "row") || heightCheck(rowIndex, col, "col")) {
                    count++
                }
            }
            println("COUNT: $count")

//            if (heightCheck(treeIndex, treeColumn) && heightCheck(treeIndex, treeRow))
        }
    }
    return count
}

fun day08Part2(input: List<List<Int>>): List<List<Int>> {
    return input.mapIndexed { rowIndex, row ->
        List(row.size) { treeIndex ->
            val col = getVertical(treeIndex, input)
            println("ROW $row COLUMN $col TREE $treeIndex")
            val rowScore = scenicScore(treeIndex, row).toList()
            val colScore = scenicScore(rowIndex, col).toList()
            println("ROW SCORE: $rowScore COL SCORE: $colScore")
            val score = rowScore + colScore
            println("PREFILTER SCORE: $score")
            val newScore = score.filter { it > 0 }
            println("FILTERED SCORE $newScore")
            println("FOLDED SCORE = ${newScore.fold(1) { acc, i -> acc * i }}")
            newScore.fold(1) { acc, i -> acc * i }
        }
    }
}
