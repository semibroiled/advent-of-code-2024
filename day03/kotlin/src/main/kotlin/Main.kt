import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText

fun main() {
    println("Day 3")
    println("Part One")

    // Load project
    val pathToInput = Path.of("../input.txt")
    val content = pathToInput.readText()

    require(pathToInput.exists()) {"No input file found in given path"}

    println(content)
    // Define Regex for Pattern matching
    val multiplePattern = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    val switchPattern = Regex("""do\(\)|don't\(\)""")
    val numericsPattern = Regex("""\d+""")
    val matchCombinedPattern = Regex("""${multiplePattern.pattern}|${switchPattern.pattern}""")

    val combinedPatternMatches = matchCombinedPattern.findAll(content)
    var product = 0
    var state = ComputeProduct.DO

    combinedPatternMatches.forEach { match ->
        when{
            (match.value.startsWith("mul") && state == ComputeProduct.DO) -> {
                check(state==ComputeProduct.DO) {"Should only multiply if DO flag is enabled"}
                multiplePattern.matchEntire(match.value)
                    ?.destructured
                    ?.toList()
                    ?.map { it.toInt() }
                    ?.let { (num1,num2) ->
                        product+=num1*num2
                    }
                }
            match.value == "do()" -> state = ComputeProduct.DO
            match.value == "don't()" -> {
                state = ComputeProduct.`DON'T`
                check(state==ComputeProduct.`DON'T`) {"DON'T flag should be enabled"}
            }

            }


    }
    println(product)

    }


