package org.example.kvmInternals.instructions.bitwise

import internals.instructions.bitwise.Bitwise
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Bitwise.not(operand1: SuperRegisterType) {
    fullRegisterWrite(
        SuperRegisterType.R3,
        fullRegisterRead(operand1).inv()
    )
}