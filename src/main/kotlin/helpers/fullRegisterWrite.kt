package helpers

import data.registers.enumIdenifiers.SuperRegisterType

/**
 * Writes a value to the specified register, regardless of its type.
 *
 * This function provides a unified way to write a value to any register ([SuperRegisterType])
 * by internally converting it to the appropriate specific register type.
 *
 * @param register The register ([SuperRegisterType]) to write to.
 * @param value The [Long] value to write to the register.
 */
fun fullRegisterWrite(register: SuperRegisterType, value: Long) {
    fullRegisterWriteUnsafe(register, value)
}