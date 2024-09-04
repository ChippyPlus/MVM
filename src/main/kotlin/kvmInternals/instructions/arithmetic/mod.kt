package org.example.kvmInternals.instructions.arithmetic

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.helpers.fullRegisterWrite

fun Arithmetic.mod(operand1: SuperRegisterType, operand2: SuperRegisterType) {
    fullRegisterWrite(SuperRegisterType.R4, fullRegisterRead(operand1) % fullRegisterRead(operand2))
}