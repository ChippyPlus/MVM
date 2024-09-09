package org.example.helpers

import org.example.data.memory.MemoryAddress
import org.example.data.memory.MemoryValue
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.internalMemory

fun writeRegisterString(register: SuperRegisterType, string: String) {
    val startingAddress = MemoryAddress(fullRegisterRead(register))
    for (i in string.indices) {
        internalMemory.write(MemoryAddress(startingAddress.address + i), MemoryValue(string[i].code))
    }
    internalMemory.write(MemoryAddress(startingAddress.address + string.length), MemoryValue(0))
}