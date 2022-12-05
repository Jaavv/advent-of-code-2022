// https://adventofcode.com/2022/day/5

fun main() {
    val testInput = readInput("Day05_test")
    val input = readInput("Day05")
    val parse = input.map { it.split("\n") }.flatten()
    val emptySpaceIndex = parse.indexOfFirst { it.isBlank() }
    val padding = parse[emptySpaceIndex - 1].length + 1
    val crates = parse.slice(0 until emptySpaceIndex - 1)
    val paddedCrates = crates.map { it.padEnd(padding, ' ').chunked(4) }.reversed()
    val parsedCrates = parseCrates(paddedCrates)
    val moves = parse.slice(emptySpaceIndex + 1 until parse.size)
    val initialStackPart1 = List(parsedCrates.size) { index -> index + 1 to parsedCrates[index] }.toMap()
    val initialStackPart2 = initialStackPart1.toMap()

//    day05Part1(moves, initialStackPart1)
//    val finalStackPart1 = initialStackPart1.values.joinToString("") { it.last() }
//    println(finalStackPart1) //LJSVLTWQM

    day05Part2(moves, initialStackPart1)
    val finalStackPart2 = initialStackPart1.values.joinToString("") { it.last() }
    println(finalStackPart2) //BRQWDBBJM
}

fun parseCrates(input: List<List<String>>): List<MutableList<String>> {
    val crates = List(input[0].size) { mutableListOf<String>() }
    for (i in input[0].indices) {
        for (value in input) {
            if (value[i].isNotBlank()) crates[i].add(value[i].filter { it.isLetter() })
        }
    }
    return crates
}

fun moveCrate(instruction: Instruction, crates: Map<Int, MutableList<String>>) {
    repeat(instruction.move) {
        val topCrate = crates[instruction.from]?.last()
        crates[instruction.from]?.removeLast()
        if (topCrate != null) {
            crates[instruction.to]?.add(topCrate)
        }
    }
}

fun moveCrates(instruction: Instruction, crates: Map<Int, MutableList<String>>) {
    val topCrates = crates[instruction.from]?.takeLast(instruction.move)
    repeat(instruction.move) {
        crates[instruction.from]?.removeLast()
    }
    if (topCrates != null) {
        crates[instruction.to]?.addAll(topCrates)
    }
}

data class Instruction(val move: Int, val from: Int, val to: Int)

fun instructionParser(input: String): Instruction {
    val parse = input.split(" ").chunked(2).associate { it.first() to it.last().toInt() }
    return Instruction(move = parse["move"]!!, from = parse["from"]!!, to = parse["to"]!!)
}

fun day05Part1(input: List<String>, crates: Map<Int, MutableList<String>>) {
    input.map { move ->
        val instruction = instructionParser(move)
        moveCrate(instruction, crates)
    }
}

fun day05Part2(input: List<String>, crates: Map<Int, MutableList<String>>) {
    input.map { move ->
        val instruction = instructionParser(move)
        moveCrates(instruction, crates)
    }
}
