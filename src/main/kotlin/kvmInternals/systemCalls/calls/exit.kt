package org.example.kvmInternals.systemCalls.calls

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.kvmInternals.classes.SystemCall
import kotlin.system.exitProcess

fun SystemCall.exit(s2: SuperRegisterType) {
    exitProcess(fullRegisterRead(s2))
}