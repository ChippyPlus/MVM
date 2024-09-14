package org.example.kvmInternals.instructions.arithmetic

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters

fun Arithmetic.mod(operand1: SuperRegisterType, operand2: SuperRegisterType) {

    try {
        val A = fullRegisterRead(operand1)
        val B = fullRegisterRead(operand2)
        returnRegisters.write(ReturnRegisterType.R4, A % B)
    } catch (e: Exception) {
        errors.ArithmeticException("Modulus operation failed")
    }
}