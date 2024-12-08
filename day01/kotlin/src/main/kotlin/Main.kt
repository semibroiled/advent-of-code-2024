// Handle Filesystem Tasks
import java.nio.file.Path
// import java.nio.file.Paths
import kotlin.io.path.*
// import java.io.File
import kotlin.math.abs

fun main() {
    // Part One
    println("Day 1")
    println("Part One")
    // Ascertain current directory
    val rootDirectory = System.getProperty("user.dir")
    println("Current root Directory: $rootDirectory")

    // Input is one directory above
    val path = Path.of("../input.txt")

    // Read contents of txt file
    val inputContent = path.readText()

    // Split by return carriage and space
    val contentAsArray = inputContent.split("\n")
    println(contentAsArray)

    // Counter for Total Distance
    var totalDistance = 0

    val leftList: MutableList<Int> = mutableListOf()
    val rightList: MutableList<Int> = mutableListOf()

    for (pairs in contentAsArray) {
        val pair = pairs.trim().split("   ").filter { it.isNotBlank() }
        // simpleArray.forEach{ println(it.toInt()) }
        if (pair.isEmpty()) continue
        // println(simpleArray)

        val leftElement: Int = pair.first().toInt()
        leftList.add(leftElement)

        val rightElement: Int = pair.last().toInt()
        rightList.add(rightElement)
        // val distance = abs(el1-el2);
        // totalDistance += distance
    }

    val rightSortedList = rightList.sorted()
    val leftSortedList = leftList.sorted()

    for ( index in rightSortedList.indices) {
        val distance = abs(leftSortedList[index]-rightSortedList[index])
        totalDistance += distance
    }

    println("Total distance: $totalDistance")

    // Part Two
    println("Part Two")

    // Running counter for similarity score
    var similarityScore = 0

    leftList.forEach() { leftNum ->
        val countInRight = rightList.count { rightNum -> rightNum == leftNum }
        similarityScore += leftNum * countInRight
    }

    println("Similarity Score: $similarityScore")

}

