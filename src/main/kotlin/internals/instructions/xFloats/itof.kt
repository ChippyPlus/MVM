package internals.instructions.xFloats

import data.registers.FDRegister
import data.registers.RegisterType
import registers

fun XFloats.itof(register: RegisterType, registerX: RegisterType) {
	val valueLong = registers.read(register)
	registers.writeX(registerX, FDRegister(false, valueLong))
}