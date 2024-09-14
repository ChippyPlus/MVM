package org.example.helpers

import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.internalMemory

fun writeRegisterString(register: SuperRegisterType, string: String): Long {
    val possibleStarts = emptyMap<Long?, Any>().toMutableMap()

    internalMemory.memory.forEach {
        possibleStarts[it.key.address] = it.value.value!!
    }
    possibleStarts.filter { it.value == 0 }
    val allocMem = string.length


    var spot: Long? = null
    for (i in possibleStarts.keys) {
        var count = 0L
        for (j in 0L..allocMem) {
            if (possibleStarts[i!! + j] == null) {
                count++
            }
        }
        if (count == allocMem + 1L) {
            spot = i
            break
        }
    }

    if (null != spot) {
        fullRegisterWrite(register, spot.toLong())
    } else {
        errors.MemoryAllocationException("Could not allocate memory for string: $string")
    }
    for ((index, i) in (spot!! until (spot + allocMem)).withIndex()) {
        internalMemory.memory[MemoryAddress(i)] = MemoryValue(string[index].code.toLong())
    }
    internalMemory.memory[MemoryAddress(spot + allocMem)] = MemoryValue(0)
    return spot


}