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
fun String.toUnsafeSuperRegisterType(): SuperRegisterType? {

	return when (this.lowercase()) {
		"g0" -> SuperRegisterType.G0
		"g1" -> SuperRegisterType.G1
		"g2" -> SuperRegisterType.G2
		"g3" -> SuperRegisterType.G3
		"g4" -> SuperRegisterType.G4
		"g5" -> SuperRegisterType.G5
		"g6" -> SuperRegisterType.G6
		"g7" -> SuperRegisterType.G7
		"g8" -> SuperRegisterType.G8
		"g9" -> SuperRegisterType.G9

		"s0" -> SuperRegisterType.S0
		"s1" -> SuperRegisterType.S1
		"s2" -> SuperRegisterType.S2
		"s3" -> SuperRegisterType.S3

		"r0" -> SuperRegisterType.R0
		"r1" -> SuperRegisterType.R1
		"r2" -> SuperRegisterType.R2
		"r3" -> SuperRegisterType.R3
		"r4" -> SuperRegisterType.R4
		"r5" -> SuperRegisterType.R5
		"r6" -> SuperRegisterType.R6
		"r7" -> SuperRegisterType.R7
		"r8" -> SuperRegisterType.R8
		"r9" -> SuperRegisterType.R9

		"f0" -> SuperRegisterType.F0
		"f1" -> SuperRegisterType.F1
		"f2" -> SuperRegisterType.F2
		"f3" -> SuperRegisterType.F3
		"f4" -> SuperRegisterType.F4
		"f5" -> SuperRegisterType.F5
		"f6" -> SuperRegisterType.F6
		"f7" -> SuperRegisterType.F7
		"f8" -> SuperRegisterType.F8
		"f9" -> SuperRegisterType.F9

		"if0" -> SuperRegisterType.IF0
		"if1" -> SuperRegisterType.IF1
		"if2" -> SuperRegisterType.IF2
		"if3" -> SuperRegisterType.IF3
		"if4" -> SuperRegisterType.IF4
		"if5" -> SuperRegisterType.IF5
		"if6" -> SuperRegisterType.IF6
		"if7" -> SuperRegisterType.IF7
		"if8" -> SuperRegisterType.IF8
		"if9" -> SuperRegisterType.IF9
		else -> null
	}
}

fun String.toSuperRegisterType(): SuperRegisterType {
	try {
		return this.toUnsafeSuperRegisterType()!!
	} catch (e: NullPointerException) {
		errors.InvalidRegisterException(this)
		exitProcess(320947)
	}
}