package helpers

import errors
import generalRegisters
import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.toGeneralRegisterType
import data.registers.enumIdenifiers.toReturnRegisterType
import data.registers.enumIdenifiers.toSystemRegisterType
import returnRegisters
import systemRegisters
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