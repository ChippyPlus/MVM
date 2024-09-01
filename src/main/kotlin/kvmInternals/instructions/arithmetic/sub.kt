package org.example.kvmInternals.instructions.arithmetic

import org.example.data.registers.enumIdenifiers.*
import org.example.helpers.RegisterAllMap
import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.returnRegisters



/**
 * Subtracts the value in register B from the value in register A, storing the result in R4.
 * Throws an IllegalStateException if the subtraction operation fails.
 */
fun Arithmetic.sub(registerA: SuperRegisterType, registerB: SuperRegisterType) {
    try {
        val currentRegisters = RegisterAllMap()
        val A = currentRegisters[registerA]
            ?: throw IllegalStateException("Current register($registerA) missing in currentRegistersMap")
        val B = currentRegisters[registerB]
            ?: throw IllegalStateException("Current register($registerB) missing in currentRegistersMap")
        val result = A - B
        returnRegisters.write(ReturnRegisterType.R4, result.toInt())
    } catch (e: Exception) {
        throw IllegalStateException("Adding failed", e)
    }
}