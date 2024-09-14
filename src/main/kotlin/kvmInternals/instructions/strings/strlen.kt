package org.example.kvmInternals.instructions.strings

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite
import org.example.internalMemory

fun Strings.strlen(addressRegister: SuperRegisterType) {
    var index = 0L
    while (true) {
        val byte = internalMemory.read(MemoryAddress(fullRegisterRead(addressRegister) + index))
        if (byte.value == 0L) break
        index++
    }
    fullRegisterWrite(SuperRegisterType.R4, index)
}