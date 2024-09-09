package org.example.kvmInternals.systemCalls.calls

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.fileDescriptors
import org.example.helpers.*
import org.example.kvmInternals.classes.SystemCall

fun SystemCall.readFile(fd: SuperRegisterType, buffer: SuperRegisterType, count: SuperRegisterType) {
    val f = fileDescriptors.getFileDescriptor(fullRegisterRead(fd))!!
    writeRegisterString(buffer, f.file.readText().substring(0..<fullRegisterRead(count)))
}