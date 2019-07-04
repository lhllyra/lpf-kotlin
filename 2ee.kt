
/*data class Node<Obj>(val data:Obj, val next:Node<Obj>?){
    override fun toString():String{
        return data.toString() + "->" + next.toString()
    }
}*/



val numListy = mutableListOf<Int>()
val repList = mutableListOf(1,2,2,3,4,5,6,6,6,7)
val unorderedList = mutableListOf(5,3,8,6,1)
//fun potencia(f:(Int)->Int, n:Int):(Int)->Int = {x -> when(n) { 0 -> x else -> multiplier(x,n) }}

fun chained(f:(Int)->Int,g:(Int)->Int):(Int)->Int = {x->f(g(x))}

fun altBiggest(f:(Int)->Int, g:(Int) -> Int):(Int)->Int = {x:Int -> if(f(x) > g(x)) f(x) else g(x)}

fun unrelatedToThree(numList:MutableList<Int>?, num:Int, aux:Int){
    when(numList?.size){
        num -> println(numList)
        else -> {
            if(aux%3!=0){numList?.add(aux)}
            unrelatedToThree(numList,num, aux+1)
        }

    }

}


fun repetition(list:List<Int>):Map<Int,Int>{
    return list.groupingBy {it}.eachCount().filter { it.value>1 }
}

fun sort(list:MutableList<Int>, current:Int, next:Int, aux: Int):MutableList<Int>{

        val curValue = list[current]
        val nextVal = list[next]

        if (curValue > nextVal) {

        }
        else{

        }

    return list

}

fun easySort(list: MutableList<Int>){
    val list2 = list.sort()
    println(list2)
}



fun main(){
    println(altBiggest({x->x*x},{x-> 2*x})(3))
    println(chained({x->x+x}, {x->x*x})(4))
    println(unrelatedToThree(numListy,10,5))
    println(repetition(repList))
    //println(sort(unorderedList,0,1, 0))
    println()
    val v1 by lazy{
        println("a")
        2
    }
    val v2 by lazy{
        println("b")
        3
    }
    println("c")
    println(v1+v2)

}
