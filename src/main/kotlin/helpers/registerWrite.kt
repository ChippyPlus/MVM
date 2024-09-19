package helpers

import data.registers.enumIdenifiers.SuperRegisterType
import data.registers.enumIdenifiers.toGeneralRegisterType
import data.registers.enumIdenifiers.toReturnRegisterType
import data.registers.enumIdenifiers.toSystemRegisterType
import errors
import generalRegisters
import returnRegisters
import systemRegisters
import kotlin.system.exitProcess

/**
 * Writes a value to the specified register, regardless of its type.
 *
 * This function provides a unified way to write a value to any register ([SuperRegisterType])
 * by internally converting it to the appropriate specific register type.
 *
 * @param register The register ([SuperRegisterType]) to write to.
 * @param value The [Long] value to write to the register.
 * @throws InvalidRegisterException If the register is a floating point number
 *
 */
fun registerWrite(register: SuperRegisterType, value: Number) {
    when (register) {
        SuperRegisterType.G1 -> generalRegisters.write(register.toGeneralRegisterType(), value as Long)
        SuperRegisterType.G2 -> generalRegisters.write(register.toGeneralRegisterType(), value as Long)
        SuperRegisterType.G3 -> generalRegisters.write(register.toGeneralRegisterType(), value as Long)
        SuperRegisterType.G4 -> generalRegisters.write(register.toGeneralRegisterType(), value as Long)
        SuperRegisterType.S1 -> systemRegisters.write(register.toSystemRegisterType(), value as Long)
        SuperRegisterType.S2 -> systemRegisters.write(register.toSystemRegisterType(), value as Long)
        SuperRegisterType.S3 -> systemRegisters.write(register.toSystemRegisterType(), value as Long)
        SuperRegisterType.S4 -> systemRegisters.write(register.toSystemRegisterType(), value as Long)
        SuperRegisterType.R1 -> returnRegisters.write(register.toReturnRegisterType(), value)
        SuperRegisterType.R2 -> returnRegisters.write(register.toReturnRegisterType(), value)
        SuperRegisterType.R3 -> returnRegisters.write(register.toReturnRegisterType(), value)
        SuperRegisterType.R4 -> returnRegisters.write(register.toReturnRegisterType(), value)
        else -> {
            errors.InvalidRegisterException(register.toString())
            exitProcess(1)
        }
    }
}