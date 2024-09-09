package org.example.kvmInternals.instructions.ioAbstractions

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead

fun IoAbstractions.printr(register: SuperRegisterType) {
    println(fullRegisterRead(register))
}