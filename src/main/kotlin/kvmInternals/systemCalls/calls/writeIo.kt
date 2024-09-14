package org.example.kvmInternals.systemCalls.calls

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.internalMemory
import org.example.kvmInternals.systemCalls.SystemCall

fun SystemCall.writeIo(address: SuperRegisterType) {
    var index = 0
    while (true) {

        val byte = internalMemory.read(
            MemoryAddress(
                fullRegisterRead(address) + index
            )
        )
        if (byte.value == 0L) break

        index++
        print(byte.value!!.toInt().toChar())

    }
}