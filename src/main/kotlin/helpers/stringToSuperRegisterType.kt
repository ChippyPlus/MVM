package helpers

import data.registers.enumIdenifiers.SuperRegisterType
import errors
import kotlin.system.exitProcess

/**
 * Converts a [String] representation of a register to its corresponding [SuperRegisterType].
 *
 * @receiver The [String] to convert (e.g. "G1", "S1", "R3").
 * @return The corresponding [SuperRegisterType].
 * @throws InvalidRegisterException If the input string is not a valid register name.
 */
fun String.toUnsafeSuperRegisterType(): SuperRegisterType {
	return when (this.uppercase()) {
		"G1" -> SuperRegisterType.G1
		"G2" -> SuperRegisterType.G2
		"G3" -> SuperRegisterType.G3
		"G4" -> SuperRegisterType.G4
		"S0" -> SuperRegisterType.S0
		"S1" -> SuperRegisterType.S1
		"S2" -> SuperRegisterType.S2
		"S3" -> SuperRegisterType.S3
		"R1" -> SuperRegisterType.R1
		"R2" -> SuperRegisterType.R2
		"R3" -> SuperRegisterType.R3
		"R4" -> SuperRegisterType.R4
		"F1" -> SuperRegisterType.F1
		"F2" -> SuperRegisterType.F2
		"F3" -> SuperRegisterType.F3
		"F4" -> SuperRegisterType.F4
		"IF1" -> SuperRegisterType.IF1
		"IF2" -> SuperRegisterType.IF2
		"IF3" -> SuperRegisterType.IF3
		"IF4" -> SuperRegisterType.IF4
		else -> error("bal")
	}
}

fun String.toSuperRegisterType(): SuperRegisterType {
	try {
		return this.toUnsafeSuperRegisterType()
	} catch (e: IllegalStateException) {
		errors.InvalidRegisterException(this)
		exitProcess(999)
	}
}