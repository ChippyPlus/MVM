package org.example.kvmInternals.instructions.arithmetic

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite


fun Arithmetic.eq(operand1: SuperRegisterType, operand2: SuperRegisterType) {
    if (fullRegisterRead(operand1) == fullRegisterRead(operand2)) {
        fullRegisterWrite(SuperRegisterType.R4, 0)
    } else {
        fullRegisterWrite(SuperRegisterType.R4, 1)
    }
}