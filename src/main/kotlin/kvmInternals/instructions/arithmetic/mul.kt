package org.example.kvmInternals.instructions.arithmetic

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters


/**
 * Multiplies the values in register A and register B, storing the result in R4.
 * Throws an IllegalStateException if the multiplication operation fails.

 */
fun Arithmetic.mul(registerA: SuperRegisterType, registerB: SuperRegisterType) {
    try {
        val A = fullRegisterRead(registerA)
        val B = fullRegisterRead(registerB)
        returnRegisters.write(ReturnRegisterType.R4, A + B)
    } catch (e: Exception) {
        errors.ArithmeticException("Multiplication operation failed")
    }
}