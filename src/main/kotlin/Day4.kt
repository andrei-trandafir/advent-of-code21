import java.awt.SystemColor
import java.io.File

class BingoBoard(private val board: Array<IntArray>){
    private val markerBoard = Array(board.size){IntArray(board.first().size){0} }

    private fun getBingo(finish: Int):Int{
        return  finish * (this.board zip this.markerBoard).flatMap { (it.first zip it.second) }
            .filter { it.second == 0 }.sumOf { it.first }
    }

    fun checkValue(value: Int): Int {
        this.board.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, cell ->
                if (cell == value){
                    this.markerBoard[rowIndex][columnIndex] = 1
                }
            }
        }
        // Check Row bingo
        val rowBingo = this.markerBoard.any { println(it.toList()); it.all { cell -> cell == 1 } }
        // Check column bingo
        val columnBingo = this.markerBoard.fold(IntArray(5){0}){ acc, next ->
            (acc zip next).map { (x, y) -> x + y }.toIntArray()
        }
        return if(rowBingo or columnBingo.any { it == this.markerBoard.size }) this.getBingo(value) else -1

    }
}

fun String.turnToBoard()= this.split("\n").map {
        it.split(" ")
            .filter{ str -> (str.isNotBlank()) and (str != "\r") }
            .map { str -> str.removeSurrounding("\r") }
            .map { str -> str.trim() }
            .map{ str -> str.toInt()}
            .toIntArray()
    }.toTypedArray()


fun main(){

    val chunks = File("C:\\Users\\andre\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\day4_1")
        .readText().split("${System.lineSeparator()}${System.lineSeparator()}")

    val bingoNumbers = chunks.first().split(",").map { it.toInt() }

    val boards = chunks.drop(1).map { it.turnToBoard() }.map { BingoBoard(it) }
    var remainingBoardIndex = -1;
    val remainingNumbers = bingoNumbers.dropWhile {
        val bingoValues = boards.map { board -> board.checkValue(it) }
        val found = bingoValues.filter { it == -1 }.size == 1
        if(found){
            val boardIndex = bingoValues.indexOfFirst { it == bingoValues.first { b -> b == -1 } }
            remainingBoardIndex = boardIndex
            println("Board ${boardIndex} with ${bingoValues[boardIndex]}")
        }
        !found
    }
    val remainingBoard = boards[remainingBoardIndex]

    remainingNumbers.takeWhile {
        val bingo = remainingBoard.checkValue(it)
        println("Bingo$it! $bingo")
        val found = bingo != -1
        !found
    }

}