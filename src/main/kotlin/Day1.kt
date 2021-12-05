import java.io.File
import kotlin.Int.Companion.MAX_VALUE


fun mainFirst() {
    val depths = File("C:\\Users\\andre\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\day1_1")
        .readLines().map { it.toInt() }
    val count = (depths.dropLast(1) zip depths.drop(1))
        .fold(0) { count, current -> count + if(current.first < current.second) 1 else 0}
    println(count)
}

fun List<Int>.getChunked() = (2 until this.size).map { listOf(this[it - 2], this[it - 1], this[it]) }


fun main(){
    val depths = File("C:\\Users\\andre\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\day1_1")
        .readLines().map { it.toInt() }
    val count = depths.getChunked().map { it.sum() }.zipWithNext()
        .fold(0) { count, current -> count + if(current.first < current.second) 1 else 0}
    println(count)
}




