// Handle Filesystem Tasks
// import java.nio.file.Path
// import java.nio.file.Paths
import kotlin.io.path.*
// import java.io.File
import java.math.BigInteger
import java.net.URI
import kotlin.math.abs

fun main() {
    // Ascertain current directory
    val rootDirectory = System.getProperty("user.dir")
    println("Current root Directory: $rootDirectory")

    // Input is one directory above
    val path = URI("/Users/acm/AdventOfCode/aoc_2024/advent-of-code-2024/day01/input.txt")

    // Read contents of txt file
    val inputContent = path.toPath().readText()

    // Split by return carriage and space
    val contentAsArray = inputContent.split("\n")
    println(contentAsArray)

    var totalDistance: Int = 0;

    val array1: MutableList<Int> = mutableListOf<Int>();
    val array2: MutableList<Int> = mutableListOf<Int>();

    for (pair in contentAsArray) {

        val simpleArray= pair.trim().split("   ").filter { it.isNotBlank() }
        simpleArray.forEach{ println(it.toInt()) }
        if (simpleArray.isEmpty()) continue
        // println(simpleArray)

        val el1: Int = simpleArray[0].toInt()
        array1.add(el1)

        val el2: Int = simpleArray[simpleArray.size.toInt() - 1].toInt()
        array2.add(el2)

        println("$el1, $el2")

        // val distance = abs(el1-el2);

        // totalDistance += distance
    }

    val firstSortedList = array1.sorted()
    val secondSortedList = array2.sorted()

    for ( i in 0..999) {
        val distance = abs(firstSortedList[i]-secondSortedList[i])
        totalDistance += distance
    }

    println("Size of first list is ${firstSortedList.size} and second list is ${secondSortedList.size}")
    println("Total distance: $totalDistance")
}

