package org.example.kvmInternals.systemCalls.calls

import org.example.data.memory.MemoryAddress
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.fileDescriptors
import org.example.helpers.fullRegisterRead
import org.example.internalMemory
import org.example.kvmInternals.classes.SystemCall


fun SystemCall.writeFile(fd: SuperRegisterType, buffer: SuperRegisterType) {
    val f = fileDescriptors.getFileDescriptor(fullRegisterRead(fd))!!
//    fullRegisterWrite(SuperRegisterType.R2, writeRegisterString(buffer, f.file.readText()))
    var index = 0
    var string = ""
    while (true) {

        val byte = internalMemory.read(
            MemoryAddress(
                fullRegisterRead(buffer) + index
            )
        )
        if (byte.value == 0) break

        index++
        string += byte.value!!.toChar()
    }
    f.file.writeText(string)
}