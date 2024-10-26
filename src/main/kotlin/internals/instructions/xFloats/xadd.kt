package internals.instructions.xFloats

import data.registers.RegisterType
import registers

fun XFloats.xAdd(operand1: RegisterType, operand2: RegisterType) {
	val o1 = registers.readFloat(operand1)
	val o2 = registers.readFloat(operand2)
	val out = o1 + o2
	registers.writeX(RegisterType.R5, out)
}