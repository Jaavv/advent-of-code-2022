// https://adventofcode.com/2022/day/4

fun main() {
    val input = readInput("Day04")
    val testinput = readInput("Day04_test")
    println(day04Part1(testinput)) //2
    println(day04Part1(input)) //542
    println(day04Part2(testinput)) //4
    println(day04Part2(input)) //900

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
