//abstract class List<out Obj>
data class Node<Obj>(val data :Obj, val next: Node<Obj>?)/*:List<Obj>()*/{
    override fun toString():String {
        return data.toString() + " -> " + next.toString()
    }
}
//object nil:List<Nothing>()


fun menorDeDois(a:Int, b:Int):Int = if (a<b) a else b

fun menorDeTres(a:Int, b:Int, c:Int):Int = if(menorDeDois(a,b)<menorDeDois(b,c)) menorDeDois(a,b) else menorDeDois(b,c)

fun fatorial(a:Int):Int = if(a==1) 1 else a*fatorial(a-1)

fun nFibonacci(n:Int):Int = if (n==1) 0 else if(n == 2) 1 else nFibonacci(n-1) + nFibonacci(n-2)

fun <Obj> getN(list:Node<Obj>?, n:Int): Obj? = when(list){
    is Node<Obj> -> if(n != 0) getN(list.next, n-1) else list.data
    else -> null
}

fun <Obj> contains(list:Node<Obj>?, entry:Obj):Boolean = when(list){
    is Node<Obj> -> if(list.data != entry) contains(list.next, entry) else true
    else -> false
}

fun <Obj> size(list:Node<Obj>?):Int = when(list){
    is Node<Obj> -> 1 + size(list.next)
    else -> 0
}

fun max(list: Node<Int>?):Int = if(list is Node<Int>) maxAux(list.next, list.data) else -1

fun maxAux(list:Node<Int>?, entry:Int):Int = when(list){
    is Node<Int> -> if(list.data>entry) maxAux(list.next, list.data) else maxAux(list.next, entry)
    else -> entry
}

fun <Obj> occ(list:Node<Obj>?, entry:Obj):Int = when(list) {
    is Node<Obj> -> if(list.data == entry) 1 + occ(list.next, entry) else occ(list.next, entry)
    else -> 0
}

fun <Obj> uniqueOcc(list:Node<Obj>?, entry:Obj):Boolean = if(list is Node<Obj>) { occ(list, entry)== 1 } else false

fun biggerThan(list:Node<Int>?, entry:Int):Node<Int>? = when(list){
    is Node<Int> -> if(list.data>entry) (Node(list.data, biggerThan(list.next, entry))) else biggerThan(list.next, entry)
    else -> null
}
//this is the longest//most explicit form of higher order/currying that can be used here
fun <Obj> concatenates(list:Node<Obj>?):(Node<Obj>?) -> Node<Obj>?{
    return fun (list2:Node<Obj>?):Node<Obj>?{
        return when(list){
            is Node<Obj> -> Node(list.data, concatenates(list.next)(list2))
            else -> list2
        }
    }
}
//this is a somewhat long/explicit form of higher order/currying that can be used here
fun <Obj> concatenates2(list:Node<Obj>?) = fun(list2:Node<Obj>?):Node<Obj>?{
    return when(list){
        is Node<Obj> -> Node(list.data, concatenates(list.next)(list2))
        else -> list2
    }
}
// this is the shortest/less explicit()because of lambdas) form of higher order/currying that can be used here
fun <Obj> concatenates3(list:Node<Obj>?) = {list2:Node<Obj>? -> when(list){
    is Node<Obj> -> Node(list.data, concatenates(list.next)(list2))
    else -> list2
}}

//TODO fun to remove the first instance of a given entry.




    fun main(){
        println(menorDeDois(3,4))
        println(menorDeTres(4,7,2))
        println(fatorial(5))
        println(nFibonacci(8))
        val list = Node(1, Node(2,Node(3,Node(4, null))))
        println(getN(list,2))
        println(contains(list, 2))
        println(size(list))
        val list2 = Node(1, Node(2,Node(3,Node(2, Node(4,null)))))
        println(occ(list2, 2))
        println(uniqueOcc(list2, 2))
        println(max(list2))
        println(biggerThan(list2, 1))
        val list3 = Node(1, Node(2, Node(3, null)))
        val list4 = Node(4, Node(5, Node(6, null)))
        println(concatenates(list3)(list4))
        println(concatenates2(list3)(list4))
        println(concatenates3(list3)(list4))

    }



