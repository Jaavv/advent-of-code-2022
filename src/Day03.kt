// https://adventofcode.com/2022/day/3

fun main() {
    val input = readInput("Day03")
    val testinput = readInput("Day03_test")
    println(day03part1(input)) //7691
    println(day03part2(input)) //2508
}

val lowerPriority = ('a'..'z').mapIndexed { index, c -> c to index + 1 }.toMap()
val upperPriority = ('A'..'Z').mapIndexed { index, c -> c to index + 27 }.toMap()
val priority = lowerPriority + upperPriority

fun day03part1(input: List<String>): Int {
    return input.sumOf { item ->
        priority.getValue(
            item.substring(0, item.length / 2).first { it in item.substring(item.length/2)}
        )
    }
}

fun day03part2(input: List<String>): Int {
    return input
        .windowed(3, 3)
        .sumOf { rucksacks ->
            priority.getValue(
                rucksacks[0].first { it in rucksacks[1] && it in rucksacks[2] }
            )
        }
}