import java.io.File


fun  CharArray.formNumber() = Integer.parseInt(this.joinToString(""), 2)

fun Pair<Int, Int>.getMoreDigits() =  when{
    this.first > this.second -> '0'
    else -> '1'
}

fun Pair<Int, Int>.getLeastDigits() =  when{
    this.first > this.second -> '1'
    else -> '0'
}

fun List<CharArray>.getCounts(index: Int) = this.fold(Pair(0,0)){acc, current -> when(current[index]){
    '0' -> Pair(acc.first + 1, acc.second)
    '1' -> Pair(acc.first, acc.second + 1)
    else -> acc
}}

fun List<CharArray>.filterOut(digitCalculator:  (Pair<Int, Int>) ->  Char): CharArray{
    return (0 until this.first().size).fold(this){ acc, current ->
        if (acc.size == 1) acc else  acc.filter { it[current] == digitCalculator(acc.getCounts(current)) }
    }.first()
}

fun main(){
    val lines = File("C:\\Users\\andre\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\day3_1")
    .readLines()
//    val lines = ("00100\n" +
//            "11110\n" +
//            "10110\n" +
//            "10111\n" +
//            "10101\n" +
//            "01111\n" +
//            "00111\n" +
//            "11100\n" +
//            "10000\n" +
//            "11001\n" +
//            "00010\n" +
//            "01010").split("\n")
        .map { it.toCharArray() }


    val oxygenRate = lines.filterOut{it.getMoreDigits()}.formNumber()
    val carbonRate = lines.filterOut{it.getLeastDigits()}.formNumber()

    println(carbonRate * oxygenRate)

}

//fun mainOne(){
//    val lines = File("C:\\Users\\andre\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\day3_1")
//        .readLines().map { it.toCharArray() }
//
//    val counts = (1..12).map { Pair(0,0) }
//    val gammaRate = lines.fold(counts) { acc, current ->
//        (acc zip current.toList()).calculateFavourites()
//    }.getMoreDigits().formNumber()
//
//    val epsilonRate = lines.fold(counts) { acc, current ->
//        (acc zip current.toList()).calculateFavourites()
//    }.getLeastDigits().formNumber()
//    println(gammaRate * epsilonRate)
//
//}