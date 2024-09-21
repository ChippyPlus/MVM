package engine

import helpers.toRegisterType


fun Parser(f: List<String>): MutableList<List<Any>> {
    val finalOut = emptyArray<List<Any>>().toMutableList()

    for (line in f) {
        val l = line.split(' ').map {
            if (it.toDoubleOrNull() != null) {
                it.toDouble()
            } else if (it.toRegisterType() != null) {
                it.toRegisterType()
            } else {
                it
            }
        }
        @Suppress("UNCHECKED_CAST") finalOut.add(l as List<Any>)
    }
    return finalOut
}
