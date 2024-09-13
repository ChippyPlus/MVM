package org.example.kvmInternals.systemCalls.calls

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.fileDescriptors
import org.example.helpers.*
import org.example.kvmInternals.classes.SystemCall
import java.io.File

fun SystemCall.openFile(registerPath: SuperRegisterType, flag: SuperRegisterType) {
    val path = readRegisterString(registerPath)
    val useFlag = fileFlags[fullRegisterRead(flag)]!!
    val f = File(path)
    if (useFlag == FileFlags.CREATE) {
        f.createNewFile()
    }
    val fd = fileDescriptors.addFileDescriptor(VMFile(f, useFlag))
    fullRegisterWrite(SuperRegisterType.R2, fd)

}