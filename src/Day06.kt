// https://adventofcode.com/2022/day/6

fun main() {
    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    check(day06Part1and2("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4) == 7)
    check(day06Part1and2("bvwbjplbgvbhsrlpgdmjqwftvncz", 4) == 5)
    check(day06Part1and2("nppdvjthqldpwncqszvftbrmjlhg", 4) == 6)
    check(day06Part1and2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4) == 10)
    check(day06Part1and2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4) == 11)

    check(day06Part1and2("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14) == 19)
    check(day06Part1and2("bvwbjplbgvbhsrlpgdmjqwftvncz", 14) == 23)
    check(day06Part1and2("nppdvjthqldpwncqszvftbrmjlhg", 14) == 23)
    check(day06Part1and2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14) == 29)
    check(day06Part1and2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14) == 26)
    println(day06Part1and2(input[0], 4)) //1538
    println(day06Part1and2(input[0], 14)) //2315
}

fun day06Part1and2(input: String, characters: Int): Int {
    val marker = input.windowed(characters).map { it.toSet() }.first { it.size == characters }.joinToString("")
    return input.indexOf(marker) + characters
}
