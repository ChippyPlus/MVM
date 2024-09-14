package org.example.kvmInternals.systemCalls.calls

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterWrite
import org.example.kvmInternals.systemCalls.SystemCall

fun SystemCall.getPid() {
    fullRegisterWrite(SuperRegisterType.R2, ProcessHandle.current().pid().toInt())

}