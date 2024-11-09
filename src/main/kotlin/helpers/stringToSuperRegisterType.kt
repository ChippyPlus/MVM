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
		"g1" -> RegisterType.G1
		"g2" -> RegisterType.G2
		"g3" -> RegisterType.G3
		"g4" -> RegisterType.G4
		"g5" -> RegisterType.G5
		"g6" -> RegisterType.G6
		"g7" -> RegisterType.G7
		"g8" -> RegisterType.G8

		"s1" -> RegisterType.S1
		"s2" -> RegisterType.S2
		"s3" -> RegisterType.S3
		"s4" -> RegisterType.S4

		"r1" -> RegisterType.R1
		"r2" -> RegisterType.R2
		"r3" -> RegisterType.R3
		"r4" -> RegisterType.R4
		"r5" -> RegisterType.R5
		"r6" -> RegisterType.R6
		"r7" -> RegisterType.R7
		"r8" -> RegisterType.R8

		"f1" -> RegisterType.F1
		"f2" -> RegisterType.F2
		"f3" -> RegisterType.F3
		"f4" -> RegisterType.F4
		"f5" -> RegisterType.F5
		"f6" -> RegisterType.F6
		"f7" -> RegisterType.F7
		"f8" -> RegisterType.F8


		"i1" -> RegisterType.I1
		"i2" -> RegisterType.I2
		"i3" -> RegisterType.I3
		"i4" -> RegisterType.I4
		"i5" -> RegisterType.I5
		"i6" -> RegisterType.I6
		"i7" -> RegisterType.I7
		"i8" -> RegisterType.I8
		"i9" -> RegisterType.I9

		"x1" -> RegisterType.X1
		"x2" -> RegisterType.X2
		"x3" -> RegisterType.X3
		"x4" -> RegisterType.X4
		"x5" -> RegisterType.X5
		"x6" -> RegisterType.X6
		"x7" -> RegisterType.X7
		"x8" -> RegisterType.X8


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