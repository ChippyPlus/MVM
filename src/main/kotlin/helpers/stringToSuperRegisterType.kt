package helpers

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import kotlin.system.exitProcess

/**
 * Converts a [String] representation of a register to its corresponding [SuperRegisterType].
 *
 * @receiver The [String] to convert (e.g. "G1", "S2", "R3").
 * @return The corresponding [SuperRegisterType].
 * @throws InvalidRegisterException If the input string is not a valid register name.
 */
fun String.toSuperRegisterType(): SuperRegisterType {
    return when (this) {
        "G1" -> SuperRegisterType.G1
        "G2" -> SuperRegisterType.G2
        "G3" -> SuperRegisterType.G3
        "G4" -> SuperRegisterType.G4
        "S1" -> SuperRegisterType.S1
        "S2" -> SuperRegisterType.S2
        "S3" -> SuperRegisterType.S3
        "S4" -> SuperRegisterType.S4
        "R1" -> SuperRegisterType.R1
        "R2" -> SuperRegisterType.R2
        "R3" -> SuperRegisterType.R3
        "R4" -> SuperRegisterType.R4
        "F1" -> SuperRegisterType.F1
        "F2" -> SuperRegisterType.F2
        "F3" -> SuperRegisterType.F3
        "F4" -> SuperRegisterType.F4
        else -> {
            errors.InvalidRegisterException(this)
            exitProcess(1) // Exit the program with the InvalidRegisterException error code(1)
            // the `exitProcess(1)` is unreachable but the compiler complains about it
        }
    }
}