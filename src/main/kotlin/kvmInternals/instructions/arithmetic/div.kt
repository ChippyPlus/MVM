package org.example.kvmInternals.instructions.arithmetic

import org.example.data.registers.enumIdenifiers.*
import org.example.helpers.RegisterAllMap
import org.example.kvmInternals.instructions.Arithmetic.Arithmetic
import org.example.returnRegisters

fun Arithmetic.div(registerA: SuperRegisterType, registerB: SuperRegisterType) {
    try {
        val currentRegisters = RegisterAllMap()
        val A = currentRegisters[registerA]
            ?: throw IllegalStateException("Current register($registerA) missing in currentRegistersMap")
        val B = currentRegisters[registerB]
            ?: throw IllegalStateException("Current register($registerB) missing in currentRegistersMap")
        val result = A / B
        returnRegisters.write(ReturnRegisterType.R4, result.toUByte())
    } catch (e: Exception) {
        returnRegisters.write(ReturnRegisterType.R1, 0u)
        throw IllegalStateException("Adding failed", e)
    }
}