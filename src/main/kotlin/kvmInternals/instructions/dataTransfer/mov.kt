package org.example.kvmInternals.instructions.dataTransfer

import org.example.data.registers.enumIdenifiers.*
import org.example.generalRegisters
import org.example.helpers.RegisterAllMap
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
        val value = currentRegisters[Source]
            ?: throw IllegalStateException("Current register($Source) missing in currentRegistersMap")
        when (Source.toString()[0]) {
            'G' -> {
                generalRegisters.write(Destination.toGeneralRegisterType(), value)
            }

            'S' -> {
                systemRegisters.write(Destination.toSystemRegisterType(), value)
            }

            'R' -> {
                returnRegisters.write(Destination.toReturnRegisterType(), value)
            }
        }
    } catch (e: Exception) {
        returnRegisters.write(ReturnRegisterType.R1,0u)
        throw IllegalStateException("Moving failed", e)
    }
}