package org.example.kvmInternals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.*
import org.example.generalRegisters
import org.example.helpers.RegisterAllMap
import org.example.helpers.fullRegisterWrite
import org.example.returnRegisters
import org.example.systemRegisters


/**
 * Moves the value from a source register to a destination register.
 * Supports all register types: General Purpose (G1-G4), System (S1-S4), and Return (R1-R4).
 * Throws an IllegalStateException if the move operation fails.
 */

fun DataTransfer.mov(Source: SuperRegisterType, Destination: SuperRegisterType) {
    try {
        val currentRegisters = RegisterAllMap()
        val value = currentRegisters[Source]!! // This shouldn't be a nullPointerException?
        fullRegisterWrite(Destination, value)
    } catch (e: Exception) {
        throw IllegalStateException("Moving failed", e)
    }
}