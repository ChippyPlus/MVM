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
	ESF,

	/**
	 * PC - Program Counter
	 */
	PC,

	SIGR,
}

val intelNames = IntelNames()

class IntelNames {
	operator fun get(registers: IntelRegisters) = when (registers) {
		IntelRegisters.ZF -> RegisterType.I1
		IntelRegisters.SF -> RegisterType.I2
		IntelRegisters.GF -> RegisterType.I3
		IntelRegisters.EF -> RegisterType.I4
		IntelRegisters.SCSF -> RegisterType.I5
		IntelRegisters.ENSF -> RegisterType.I6
		IntelRegisters.ESF -> RegisterType.I7
		IntelRegisters.PC -> RegisterType.I8
		IntelRegisters.SIGR -> RegisterType.I9

	}
}