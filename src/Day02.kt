// https://adventofcode.com/2022/day/2

fun main() {
    val input = readInput("Day02")
    val testinput = readInput("Day02_test")
    println(Day02Part1(input)) // 12276
    println(Day02Part2(input)) // 9975
}

fun rock(vs: String): Int {
   val result = when (vs) {
       "A" -> 3
       "B" -> 0
       "C" -> 6
       else -> 0
   }
    return result + 1
}

fun paper(vs: String): Int {
    val result = when (vs) {
        "A" -> 6
        "B" -> 3
        "C" -> 0
        else -> 0
    }
    return result + 2
}

fun scissors(vs: String): Int {
    val result = when (vs) {
        "A" -> 0
        "B" -> 6
        "C" -> 3
        else -> 0
    }
    return result + 3
}

fun RPS(opponent: String, player: String): Int {
    return when (player) {
        "X" -> rock(opponent)
        "Y" -> paper(opponent)
        "Z" -> scissors(opponent)
        else -> 0
    }
}

fun winPart2(vs: String): Int {
    return when (vs) {
        "A" -> paper(vs)
        "B" -> scissors(vs)
        "C" -> rock(vs)
        else -> 0
    }
}

fun losePart2(vs: String): Int {
    return when (vs) {
        "A" -> scissors(vs)
        "B" -> rock(vs)
        "C" -> paper(vs)
        else -> 0
    }
}

fun drawPart2(vs: String): Int {
    return when (vs) {
        "A" -> rock(vs)
        "B" -> paper(vs)
        "C" -> scissors(vs)
        else -> 0
    }
}

fun RPSpart2(opponent: String, player: String): Int {
    return when (player) {
        "X" -> losePart2(opponent)
        "Y" -> drawPart2(opponent)
        "Z" -> winPart2(opponent)
        else -> 0
    }
}

fun Day02Part1(input: List<String>): Int {
    return input.sumOf { round ->
        val hands = round.split(" ")
        RPS(hands.first(), hands.last())
    }
}

fun Day02Part2(input: List<String>): Int {
    return input.sumOf { round ->
        val hands = round.split(" ")
        RPSpart2(hands.first(), hands.last())
    }
}
