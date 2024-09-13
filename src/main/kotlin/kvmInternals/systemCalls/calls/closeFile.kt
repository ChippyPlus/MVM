package org.example.kvmInternals.systemCalls.calls

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.fileDescriptors
import org.example.helpers.fullRegisterRead
import org.example.kvmInternals.classes.SystemCall

fun SystemCall.closeFile(s2: SuperRegisterType) {
    val fd = fullRegisterRead(s2)
    fileDescriptors.fds.remove(fd)
}