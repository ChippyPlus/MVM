package org.example.kvmInternals.systemCalls.calls

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.internalMemory
import org.example.kvmInternals.classes.SystemCall

fun SystemCall.writeIo(address: SuperRegisterType, len: SuperRegisterType) {
    for (i in 0 until fullRegisterRead(len)) {
        val byte = internalMemory.read(
            MemoryAddress(
                fullRegisterRead(address) + i
            )
        )
        print(byte.value.toChar())
    }
}