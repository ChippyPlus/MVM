package org.example.kvmInternals.instructions.arithmetic

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters

/**
 * Divides the value in register A by the value in register B and stores the result in R4.
 * Throws an IllegalStateException if the division operation fails (e.g., division by zero).
 */
fun Arithmetic.div(registerA: SuperRegisterType, registerB: SuperRegisterType) {
    try {
        val A = fullRegisterRead(registerA)
        val B = fullRegisterRead(registerB)
        returnRegisters.write(ReturnRegisterType.R4, A / B)
    } catch (e: Exception) {
        errors.ArithmeticException("Division operation failed")
    }
}