import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

// https://adventofcode.com/2022/day/4

@OptIn(ExperimentalTime::class)
fun main() {
    val input = readInput("Day04")
    val testinput = readInput("Day04_test")
    println(day04Part1(testinput)) //2
    println(day04Part1(input)) //542
    println(day04Part2(testinput)) //4
    println(day04Part2(input)) //900

    val timeOfCount = measureTimedValue {
        day04partCount(input, ::coveredSection)
        day04partCount(input, ::overlap)
    }
    println("function Count, Duration: ${timeOfCount.duration}") // function Count, Duration: 12.881300ms

    val timeOfFilterSize = measureTimedValue {
        day04partFilterSize(input, ::coveredSection)
        day04partFilterSize(input, ::overlap)
    }
    println("function FilterSize, Duration: ${timeOfFilterSize.duration}") // function FilterSize, Duration: 4.876800ms

}

fun coveredSection(inp: String): Boolean {
    val sections = inp.split("-", ",").map { it.toInt() }
    return (sections[0] <= sections[2] && sections[1] >= sections[3]) || (sections[2] <= sections[0] && sections[3] >= sections[1])
}

fun day04Part1(input: List<String>): Int {
    return input.filter { coveredSection(it) }.size
}

fun overlap(inp: String): Boolean {
    val sections = inp.split("-", ",").map { it.toInt() }
    return (sections[0]..sections[1]).any { it in (sections[2]..sections[3]) }
}
fun day04Part2(input: List<String>): Int {
    return input.filter { overlap(it) }.size
}

fun day04partCount(input: List<String>, func: (String) -> Boolean): Int {
    return input.count { func(it) }
}

fun day04partFilterSize(input: List<String>, func: (String) -> Boolean): Int {
    return input.filter { func(it) }.size
}
