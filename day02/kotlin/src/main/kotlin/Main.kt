import kotlin.io.path.*
import java.nio.file.Path

fun main() {
    println("Project Initialized")

    println("Day 2")
    // println("Part One")

    // Read File
    val pathToInput = Path.of("../input.txt")
    require(pathToInput.exists()) {"Input doesn't exist"}
    val inputContent = pathToInput.readText()
    // println(inputContent)
    val reportsAsList = inputContent.split("\n").filter { it.isNotEmpty() }

    println(reportsAsList)
    // Counter for Safe Reports
    var safeReports = 0
    var numberOfReports = 0

    report@for (report in reportsAsList) {
        val reportAsList = report.split(" ")

        if(reportAsList.size <= 1) {
            // println("${reportAsList.isEmpty()}")
            continue@report
        }

        // Part One
        val safelyMonotoneDecreasing = reportAsList.windowed(2).all { (firstElement, secondElement) ->
            val a = firstElement.toInt()
            val b = secondElement.toInt()
            a > b && (a - b) in 1..3
        }

        val safelyMonotoneIncreasing = reportAsList.windowed(2).all { (firstElement, secondElement) ->
            val a = firstElement.toInt()
            val b = secondElement.toInt()
            a<b && (b - a) in 1..3
        }

        println("List $reportAsList: safeMonotoneIncrease is $safelyMonotoneIncreasing, safeMonotoneDecrease is $safelyMonotoneDecreasing")
        // Definitely Safe
        if(safelyMonotoneIncreasing || safelyMonotoneDecreasing) {
            println("This list is safe")
            safeReports+=1
            continue@report
        }

        println("This list isn't safe, let's check again")
        // If Unsafe, check if removing one numbers results in it being safe again
        failsafe@for ( i in reportAsList.indices) {

            // val amendedList = reportAsList.toMutableList().removeAt(i).toList()
            val amendedReport = reportAsList.filterIndexed { index, _ -> index != i }.map { it.toInt() }
             val isSafelyMonotoneDecreasing = amendedReport.windowed(2).all { (firstElement, secondElement) ->
                 firstElement > secondElement && (firstElement - secondElement) in 1..3
            }

            val isSafelyMonotoneIncreasing = amendedReport.windowed(2).all { (firstElement, secondElement) ->
                firstElement < secondElement && (secondElement - firstElement) in 1..3
            }

            println("List $amendedReport: safeMonotoneIncrease is $isSafelyMonotoneIncreasing, safeMonotoneDecrease is $isSafelyMonotoneDecreasing")
            // Definitely Safe
            if(isSafelyMonotoneIncreasing || isSafelyMonotoneDecreasing) {
                println("The amended list $amendedReport is safe")
                safeReports+=1
                break@failsafe
            }

        }
        println("Neither or. $reportAsList is unsafe!!")
        numberOfReports+=1

    }

    println("$numberOfReports/${reportsAsList.size} checked")
    println("$safeReports reports are safe")

}