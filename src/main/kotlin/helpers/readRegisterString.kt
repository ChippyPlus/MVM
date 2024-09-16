package org.example.helpers

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.internalMemory

fun readRegisterString(register: SuperRegisterType): String {
    var index = 0
    var string = ""
    while (true) {
        val byte = internalMemory.read(MemoryAddress(fullRegisterRead(register) + index))
        if (byte.value == 0L) break
        string += byte.value!!.toInt().toChar()
        index++
    }
    return string
}