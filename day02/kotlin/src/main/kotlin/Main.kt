import kotlin.io.path.*
import java.nio.file.Path

fun main() {
    println("Project Initialized")

    println("Day 2")
    println("Part One")

    // Read File
    val pathToInput = Path.of("../input.txt")
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
            continue
        }

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
        if(safelyMonotoneIncreasing || safelyMonotoneDecreasing) {
            safeReports+=1
        }
        println("Neither or. $reportAsList is unsafe!!")
        numberOfReports+=1
//        for (index in reportAsList.indices) {
//            println("Checking index $index")
//
//            // skip check if next index in report doesn't exist
//            if ((index+1) !in reportAsList.indices) {
//                println("Current index is $index and next index is ${index+1} so skipping check")
//                continue}
//
//            // Check if all increasing or decreasing!
//
//            val difference = abs(reportAsList[index].toInt() - reportAsList[index+1].toInt())
//            if (difference in 1..3) {
//                println("Difference is $difference, we are safe")
//                // do nothing if safe for current check
//                continue
//            }  else {
//                println("Uhoh, difference is $difference. Lets look at the next report")
//                println("This report $report isnt safe")
//                // skip report loop if not safe
//                continue@report
//            }
//        }
//        println("All parameters were safe, so report $report is ok")
//        // If inner loop not skipped, that means every thing was safe
//        safeReports+=1


    }

    println("$numberOfReports/${reportsAsList.size} checked")
    println("Safe Reports: $safeReports")
}