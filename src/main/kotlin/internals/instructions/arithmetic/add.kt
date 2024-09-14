package org.example.kvmInternals.instructions.arithmetic

import internals.instructions.arithmetic.Arithmetic
import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters


/**
 * Adds the values in register A and register B, storing the result in R4.
 * Throws an IllegalStateException if the addition operation fails.
 */
fun Arithmetic.add(registerA: SuperRegisterType, registerB: SuperRegisterType) {
    try {
        val A = fullRegisterRead(registerA)
        val B = fullRegisterRead(registerB)
        returnRegisters.write(ReturnRegisterType.R4, A + B)
    } catch (e: Exception) {
        errors.ArithmeticException("Addition operation failed")
    }
}