package helpers

import data.registers.RegisterType
import errors
import kotlin.system.exitProcess

/**
 * Converts a [String] representation of a register to its corresponding [RegisterType].
 *
 * @receiver The [String] to convert (e.g. "G1", "S1", "R3").
 * @return The corresponding [RegisterType].
 * @throws InvalidRegisterException If the input string is not a valid register name.
 */
fun String.toUnsafeRegisterType(): RegisterType? {


	return when (this.lowercase()) {
		"g0" -> RegisterType.G0
		"g1" -> RegisterType.G1
		"g2" -> RegisterType.G2
		"g3" -> RegisterType.G3
		"g4" -> RegisterType.G4
		"g5" -> RegisterType.G5
		"g6" -> RegisterType.G6
		"g7" -> RegisterType.G7
		"g8" -> RegisterType.G8
		"g9" -> RegisterType.G9

		"s0" -> RegisterType.S0
		"s1" -> RegisterType.S1
		"s2" -> RegisterType.S2
		"s3" -> RegisterType.S3

		"r0" -> RegisterType.R0
		"r1" -> RegisterType.R1
		"r2" -> RegisterType.R2
		"r3" -> RegisterType.R3
		"r4" -> RegisterType.R4
		"r5" -> RegisterType.R5
		"r6" -> RegisterType.R6
		"r7" -> RegisterType.R7
		"r8" -> RegisterType.R8
		"r9" -> RegisterType.R9

		"f0" -> RegisterType.F0
		"f1" -> RegisterType.F1
		"f2" -> RegisterType.F2
		"f3" -> RegisterType.F3
		"f4" -> RegisterType.F4
		"f5" -> RegisterType.F5
		"f6" -> RegisterType.F6
		"f7" -> RegisterType.F7
		"f8" -> RegisterType.F8
		"f9" -> RegisterType.F9

		"if0" -> RegisterType.IF0
		"if1" -> RegisterType.IF1
		"if2" -> RegisterType.IF2
		"if3" -> RegisterType.IF3
		"if4" -> RegisterType.IF4
		"if5" -> RegisterType.IF5
		"if6" -> RegisterType.IF6
		"if7" -> RegisterType.IF7
		"if8" -> RegisterType.IF8
		"if9" -> RegisterType.IF9

		"i0" -> RegisterType.I0
		"i1" -> RegisterType.I1
		"i2" -> RegisterType.I2
		"i3" -> RegisterType.I3
		"i4" -> RegisterType.I4
		"i5" -> RegisterType.I5
		"i6" -> RegisterType.I6
		"i7" -> RegisterType.I7
		"i8" -> RegisterType.I8
		"i9" -> RegisterType.I9


		else -> null
	}
}

fun String.toRegisterType(): RegisterType {
	try {
		return this.toUnsafeRegisterType()!!
	} catch (e: NullPointerException) {
		errors.InvalidRegisterException(this)
		exitProcess(320947)
	}
}