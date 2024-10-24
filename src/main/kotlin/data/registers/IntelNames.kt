package data.registers


/**
 * * ZF - Zero Flag
 * * SF - Sign Flag
 * * GF - Greater Flag
 * * SCSF - System Call Success Flag
 * * ENSF - Error Non Specific Flag
 * * ESF - Error Specific Flag
 */
enum class IntelRegisters {
	/**
	 * ZF - Zero Flag
	 */
	ZF,

	/**
	 * SF - Sign Flag
	 */
	SF,

	/**
	 * GF - Greater Flag
	 */
	GF,

	/**
	 * EF - Equal Flag
	 */
	EF,

	/**
	 * SCSF - System Call Success Flag
	 */
	SCSF,

	/**
	 * ENSF - Error Non-Specific Flag
	 */
	ENSF,

	/**
	 * ESF - Error Specific Flag
	 */
	ESF
}

val intelNames = IntelNames()

class IntelNames {
	operator fun get(registers: IntelRegisters) = when (registers) {
		IntelRegisters.ZF -> RegisterType.I0
		IntelRegisters.SF -> RegisterType.I1
		IntelRegisters.GF -> RegisterType.I2
		IntelRegisters.EF -> RegisterType.I3
		IntelRegisters.SCSF -> RegisterType.I4 // todo, actually implement
		IntelRegisters.ENSF -> RegisterType.I5
		IntelRegisters.ESF -> RegisterType.I6

	}
}