fun main() {
    fun part1(input: List<String>): Int {
        val calories = mutableListOf<Int>()
        var acc = 0
        for (i in input) {
           if (i == "")  {
               calories.add(acc)
               acc = 0
           } else {
               acc += i.toInt()
           }
        }
        return calories.max()
    }

    fun part2(input: List<String>): Int {
        val calories = mutableListOf<Int>()
        var acc = 0
        for (i in input) {
            if (i == "")  {
                calories.add(acc)
                acc = 0
            } else {
                acc += i.toInt()
            }
        }
        var sumOfThree = 0
        repeat(3) {
            sumOfThree += calories.max()
            calories.remove(calories.max())
        }
        return sumOfThree
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input)) //71023
    println(part2(input)) //206289
}