package org.example.kvmInternals.instructions.bitwise

import kvmInternals.instructions.bitwise.Bitwise
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Bitwise.not(operand1: SuperRegisterType) {
    fullRegisterWrite(
        SuperRegisterType.R4,
        fullRegisterRead(operand1)!!.inv()
    )
}