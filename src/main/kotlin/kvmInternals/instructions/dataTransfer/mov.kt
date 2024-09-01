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

        when (Destination) {
            SuperRegisterType.G1 -> generalRegisters.write(Destination.toGeneralRegisterType(), value)
            SuperRegisterType.G2 -> generalRegisters.write(Destination.toGeneralRegisterType(), value)
            SuperRegisterType.G3 -> generalRegisters.write(Destination.toGeneralRegisterType(), value)
            SuperRegisterType.G4 -> generalRegisters.write(Destination.toGeneralRegisterType(), value)
            SuperRegisterType.S1 -> systemRegisters.write(Destination.toSystemRegisterType(), value)
            SuperRegisterType.S2 -> systemRegisters.write(Destination.toSystemRegisterType(), value)
            SuperRegisterType.S3 -> systemRegisters.write(Destination.toSystemRegisterType(), value)
            SuperRegisterType.S4 -> systemRegisters.write(Destination.toSystemRegisterType(), value)
            SuperRegisterType.R1 -> returnRegisters.write(Destination.toReturnRegisterType(), value)
            SuperRegisterType.R2 -> returnRegisters.write(Destination.toReturnRegisterType(), value)
            SuperRegisterType.R3 -> returnRegisters.write(Destination.toReturnRegisterType(), value)
            SuperRegisterType.R4 -> returnRegisters.write(Destination.toReturnRegisterType(), value)
        }


    } catch (e: Exception) {
        throw IllegalStateException("Moving failed", e)
    }
}