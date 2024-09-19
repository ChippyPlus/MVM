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
 * Reads the value from the specified register, regardless of its type.
 *
 * This function provides a unified way to access the value of any register ([SuperRegisterType])
 * by internally converting it to the appropriate specific register type.
 *
 * @param register The register ([SuperRegisterType]) to read from.
 * @return The value stored in the register as a [Long].
 * @throws NullRegisterException If the register has not been initialised (has a null value).
 * @throws InvalidRegisterException If the register is a floating point number
 */

fun registerRead(register: SuperRegisterType): Number {
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
            else -> {
                errors.InvalidRegisterException(register.toString())
                exitProcess(1)
            }
        }
    } catch (e: NullPointerException) {
        errors.NullRegisterException(register)
        exitProcess(11) // To satisfy the compiler. This shouldn't trigger
    }
}