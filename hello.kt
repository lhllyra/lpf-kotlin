

data class Node<Obj>(val info:Obj, val next:Node<Obj>?) {

    override fun toString():String {
        return info.toString() + " -> " + next.toString()
        }

    }

    fun <Obj> length(list: Node<Obj>?):Int = when(list){
        is Node<Obj> -> length(list.next) + 1
        else -> 0
    }

    fun <Obj> concatenate(list:Node<Obj>?) = {list2:Node<Obj>? -> when }

    fun <Obj> compare(n1:Node<Obj>?, n2:Node<Obj>?):Boolean{
        if(n1 == null)
            return false
        else if(n2 == null)
            return false
        else
            return n1 == n2


    }

    fun <Obj> intercalates(n1:Node<Obj>?, n2:Node<Obj>?):Node<Obj>?{
        if(n1 == null && n2 == null){
            return null
        }
        else if(n1 == null){
            return Node(n2!!.info, intercalates(n1, n2.next))
        }

        else{
            return Node(n1.info, intercalates(n2, n1.next))
        }
    }

    fun <Obj> find(data:Obj, n:Node<Obj>?):Boolean{
        if(n==null) return false
        else
            if(n.info == data) return true
            else return find(data, n.next)

    }

    fun <Obj> insertOnTail(data:Obj, n:Node<Obj>?):Node<Obj>{
        if(n == null) return Node(data, null)
        else if(n.next != null) return Node(n.info, insertOnTail(data, n.next))
        else return Node(n.info, Node(data, null))
    }

    fun <Obj> insertOnHead(data:Obj, n:Node<Obj>?):Node<Obj>?{
        if(n == null) return null
        else return Node(data, n)
    }

    fun <Obj> validIndex(index:Int, n:Node<Obj>?):Boolean{
        return index<length(n) && index > 0
    }

    fun <Obj> insertToIndex(data:Obj, index:Int, n:Node<Obj>?):Node<Obj>?{
        if(n==null) return null

        else if(index == 0) return insertOnHead(data,n)

        else if(index == length(n)) return insertOnTail(data,n)

        else if(validIndex(index, n)) return insertToIndex(data, index, n, 0)

        else println("unreachable index"); return n


    }

    fun <Obj> insertToIndex(data:Obj, index:Int, n:Node<Obj>?, aux:Int):Node<Obj>? = when {
        n == null -> null
        aux < index -> Node(n.info, insertToIndex(data, index, n.next, aux+1))
        aux > index -> Node(n.info, insertToIndex(data ,index,n.next,aux+1))
        else -> Node(data, insertToIndex(data ,index ,n,aux+1))
    }

    fun <Obj> removeFromIndex(index:Int ,n: Node<Obj>?):Node<Obj>?{
        if(n==null) return null

        else if(index==0) return removeFromIndex(0, n,0)

        else if(index==length(n)-1) return removeFromIndex(length(n)-1, n, 0)

        else if(validIndex(index,n)) return removeFromIndex(index, n, 0)

        else println("unreachable Index."); return n


    }

    fun <Obj> removeFromIndex(index: Int, n: Node<Obj>?, aux:Int):Node<Obj>? {
        if(n==null) return null
        else if(aux<index) return Node(n.info, removeFromIndex(index,n.next,aux+1))
        else if(index == aux) return removeFromIndex(index,n.next,aux+1)
        else if (aux>index ) return Node(n.info, removeFromIndex(index,n.next,aux+1))
        else println("unreachable index."); return n

    }

    fun <Obj> invert(n:Node<Obj>?):Node<Obj>?{
        if(n == null)
            return null
        else
            return concatenate(invert(n.next), Node(n.info, null))
    }

    fun <Obj> isPalindrome(n1:Node<Obj>?):Boolean{
        if(n1 == null)
            return false

        else
            return compare(n1, invert(n1))

    }

    fun <Obj> compress(n1:Node<Obj>?):Node<Obj>?{
        if(n1==null)
            return null
        else if(n1!=n1.next)
            return Node(n1.info, compress(n1.next))
        else
            return compress(n1.next)

    }

    fun map(n:Node<Int>?, f:(Int)->Int):Node<Int>? = if (n == null) null else Node(f(n.info), map(n.next, f))

    fun <Obj> filter(list:Node<Obj>?, f:(Obj)->Boolean):Node<Obj>? = when(list){
        is Node<Obj> -> if(f(list.info)) Node(list.info, filter(list.next, f)) else filter(list.next, f)
        else -> null
    }

    fun reduce(list:Node<Int>?,neutral:Int, f:(Int, Int)->Int):Int = when(list){
        is Node<Int> -> f(list.info, reduce(list.next, neutral, f))
        else -> neutral

    }

    //TODO compose()



/* since kotlin 1.3 it is not necessary to declare any arguments on your main function. */
fun main() {
        val n1=Node(1,Node(2,null))
        val n2=Node(3, Node(4, Node(5, null)))
        val n3 = Node(1, Node(2, Node(3, null)))
        val n4 = Node(4, Node(5, Node(6, null)))
        println(n1)
        println(length(n1))
        println(n2)
        println(length(n2))
        println(concatenate(n1, n2))
        println(intercalates(n3, n4))
        println(find(2, n2) )
        println(find(2, n1))
        val n7 = (insertOnTail(9,n2))
        println(n7)
        val n8 = insertOnTail(7,n7)
        println(n8)
        val n9 = (insertOnHead(6,n8))
        println("here is n9 $n9")
        val n10 =  insertToIndex(0,3, n9)
        println("here is n10 $n10")
        val n11 = (removeFromIndex(3, n10))
        println("here is n11 $n11")
        //val k = Node(1,Node(2, Node(3,null)))
        val j = invert(n11)
        println(j)
        val pal = Node("m",Node("a",Node("d",Node("a",Node("m",null)))))
        val pal1 = Node("m",Node("i",Node("d",Node("a",Node("m",null)))))
        println(isPalindrome(pal))
        println(isPalindrome(pal1))
        val comp = Node(1, Node(1, Node(2,Node(3,Node(3,null)))))
        println(compress(comp))
        val result = reduce(comp, 0) { x, y -> x+y}
        println(result)
        val result2 = map(comp){x -> x*2}
        println(result2)
        val result3 = filter(comp){x-> x!=2}
        println(result3)


}



