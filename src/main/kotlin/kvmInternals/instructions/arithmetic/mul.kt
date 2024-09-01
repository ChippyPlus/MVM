package org.example.kvmInternals.instructions.arithmetic

import org.example.data.registers.enumIdenifiers.*
import org.example.helpers.RegisterAllMap
import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.returnRegisters



/**
 * Multiplies the values in register A and register B, storing the result in R4.
 * Throws an IllegalStateException if the multiplication operation fails.

 */
fun Arithmetic.mul(registerA: SuperRegisterType, registerB: SuperRegisterType) {
    try {
        val currentRegisters = RegisterAllMap()
        val A = currentRegisters[registerA]
            ?: throw IllegalStateException("Current register($registerA) missing in currentRegistersMap")
        val B = currentRegisters[registerB]
            ?: throw IllegalStateException("Current register($registerB) missing in currentRegistersMap")
        val result = A * B
        returnRegisters.write(ReturnRegisterType.R4, result.toInt())
    } catch (e: Exception) {
        throw IllegalStateException("Adding failed", e)
    }
}