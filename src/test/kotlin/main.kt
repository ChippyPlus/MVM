package test
data class MemByte(val data: Int?, var occupied: Boolean, var collection: Int?)


class Memory(val size: Int = 64) {
    val newMemory = mutableListOf<MemByte>()

    init {
        for (i in 0 until size) {
            newMemory.add(
                MemByte(
                    data = null, occupied = false, collection = null
                )
            )
        }
    }


    var currentCollections = -1
    fun allocate(inSize: Int): Int? {
        var freeCount = 0
        var possibleAddress: Int? = null

//        while (freeCount != inSize) {
//
//        }
        for (i in 0..size) {
            if (freeCount == inSize) {
                possibleAddress = i
                break
            }
            if (!newMemory[i].occupied) {
                freeCount++
            }

        }
        if (possibleAddress != null) {
            currentCollections++
            for (i in possibleAddress until possibleAddress) {
                newMemory[i] = MemByte(
                    data = null, occupied = true, collection = 99
                )
            }
            return possibleAddress - freeCount
        } else {
            return null
        }
    }

    fun set(address: Int, data: IntArray) {
        for (i in data.indices) {
            newMemory[i + address] = MemByte(
                data = data[i], occupied = true, collection = currentCollections
            )
        }
    }
}

fun main() {
    val w = Worky()
    println(w.setFirstName("mo mo"))
    println(w.firstName)


//    val m = Memory()
//    m.set(m.allocate(4)!!, intArrayOf(1, 2, 3, 4))
//    m.set(m.allocate(4)!!, intArrayOf(11, 22, 33, 44))
////    m.set(m.allocate(4)!!, intArrayOf(111, 222, 333, 444))
//
//
//    for (i in m.newMemory) {
//        println(i)
//    }
}