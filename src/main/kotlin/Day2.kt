import java.io.File


fun main(){
    val example = "forward 5\n" +
            "down 5\n" +
            "forward 8\n" +
            "up 3\n" +
            "down 8\n" +
            "forward 2"

    val commands = File("C:\\Users\\andre\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\day2_1")
        .readLines()
//    val commands = example.split("\n")
        .map { it.split(" ").zipWithNext().first() }.map { (x, y) -> Pair(x, y.toInt()) }

    val commandMap = mutableMapOf<String, Int>()
    commands.forEach { (command, value) -> when(command){
        "forward" -> commandMap += mapOf("horizontal" to (commandMap["horizontal"]?:0) + value, "depth" to (commandMap["depth"]?: 0) + (commandMap["aim"]?: 0) * value)
        "down" -> commandMap += ("aim" to (commandMap["aim"] ?: 0) + value)
        "up" -> commandMap += ("aim" to (commandMap["aim"] ?: 0) - value)
    }}

    println(commandMap["depth"]!! * commandMap["horizontal"]!!)

}