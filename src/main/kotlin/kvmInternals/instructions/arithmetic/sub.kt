package org.example.kvmInternals.instructions.arithmetic

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.data.registers.enumIdenifiers.ReturnRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.errors
import org.example.helpers.fullRegisterRead
import org.example.returnRegisters


/**
 * Subtracts the value in register B from the value in register A, storing the result in R4.
 * Throws an IllegalStateException if the subtraction operation fails.
 */
fun Arithmetic.sub(registerA: SuperRegisterType, registerB: SuperRegisterType) {
    try {
        val A = fullRegisterRead(registerA)
        val B = fullRegisterRead(registerB)
        returnRegisters.write(ReturnRegisterType.R4, A - B)
    } catch (e: Exception) {
        errors.ArithmeticException("Subtraction operation failed")
    }
}