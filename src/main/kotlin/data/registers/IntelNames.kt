package data.registers


enum class IntelRegisters {
	ZF, SF, GF, EF, NF, STF, MAF, SCSF
}


class IntelNames {
	fun mapToRegister(registers: IntelRegisters) = when (registers) {
		IntelRegisters.ZF -> RegisterType.I0
		IntelRegisters.SF -> RegisterType.I1
		IntelRegisters.GF -> RegisterType.I2
		IntelRegisters.EF -> RegisterType.I3
		IntelRegisters.NF -> RegisterType.I4
		IntelRegisters.STF -> RegisterType.I5
		IntelRegisters.MAF -> RegisterType.I6
		IntelRegisters.SCSF -> RegisterType.I7
	}
}