package org.example.kvmInternals.instructions.strings

import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterWrite
import org.example.internalMemory


//TODO Make more efficient and add description
fun Strings.str(targetAddress: SuperRegisterType, string: String) {
    val possibleStarts: MutableMap<Int, Any?> = emptyMap<Int, Any>().toMutableMap()

    internalMemory.memory.forEach {
        possibleStarts[it.key.address] = it.value.value
    }
    possibleStarts.filter { it.value == 0 }
    val allocMem = string.length


    var spot: Int? = null
    for (i in possibleStarts.keys) {
        var count = 0
        for (j in 0..allocMem) {
            if (possibleStarts[i + j] == null) {
                count++
            }
        }
        if (count == allocMem + 1) {
            spot = i
            break
        }
    }

    if (null != spot) {
        fullRegisterWrite(targetAddress, spot)
    } else {
        errors.MemoryAllocationException("Could not allocate memory for string: $string")
    }
    for ((index, i) in (spot!! until (spot + allocMem)).withIndex()) {
        internalMemory.memory[MemoryAddress(i)] = MemoryValue(string[index].code)
    }
    internalMemory.memory[MemoryAddress(spot + allocMem)] = MemoryValue(0)

    println(internalMemory.memory)
}