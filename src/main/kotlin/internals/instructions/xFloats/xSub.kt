package internals.instructions.xFloats

import data.registers.FDRegister
import data.registers.RegisterType
import registers


fun XFloats.xSub(operand1: RegisterType, operand2: RegisterType) {
	val o1 = registers.readX(operand1)
	val o2 = registers.readX(operand2)

	if (o1.isDouble && o2.isDouble) {
		val out = (Double.fromBits(o1.value) - Double.fromBits(o2.value)).toBits()
		registers.writeX(RegisterType.R5, FDRegister(true, out))
	} else {
		val out = (Float.fromBits(o1.value.toInt()) - Float.fromBits(o2.value.toInt())).toBits().toLong()
		registers.writeX(RegisterType.R5, FDRegister(false, out))
	}
}