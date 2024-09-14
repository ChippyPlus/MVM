package org.example.helpers

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.data.registers.enumIdenifiers.toGeneralRegisterType
import org.example.data.registers.enumIdenifiers.toReturnRegisterType
import org.example.data.registers.enumIdenifiers.toSystemRegisterType
import org.example.errors
import org.example.generalRegisters
import org.example.returnRegisters
import org.example.systemRegisters
import kotlin.system.exitProcess

fun fullRegisterRead(register: SuperRegisterType): Long {
    return try {
        when (register) {
            SuperRegisterType.G1 -> generalRegisters.read(register.toGeneralRegisterType())
            SuperRegisterType.G2 -> generalRegisters.read(register.toGeneralRegisterType())
            SuperRegisterType.G3 -> generalRegisters.read(register.toGeneralRegisterType())
            SuperRegisterType.G4 -> generalRegisters.read(register.toGeneralRegisterType())
            SuperRegisterType.S1 -> systemRegisters.read(register.toSystemRegisterType())
            SuperRegisterType.S2 -> systemRegisters.read(register.toSystemRegisterType())
            SuperRegisterType.S3 -> systemRegisters.read(register.toSystemRegisterType())
            SuperRegisterType.S4 -> systemRegisters.read(register.toSystemRegisterType())
            SuperRegisterType.R1 -> returnRegisters.read(register.toReturnRegisterType())
            SuperRegisterType.R2 -> returnRegisters.read(register.toReturnRegisterType())
            SuperRegisterType.R3 -> returnRegisters.read(register.toReturnRegisterType())
            SuperRegisterType.R4 -> returnRegisters.read(register.toReturnRegisterType())
        }
    } catch (e: NullPointerException) {
        errors.NullRegisterException(register)
        exitProcess(11) // To satisfy the compiler. This shouldn't trigger
    }
}