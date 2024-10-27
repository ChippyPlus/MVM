package internals.instructions.xFloats

import data.registers.RegisterType
import registers

fun XFloats.xLit(registerX: RegisterType, valueX: Number) {
	registers.writeX(registerX, valueX)
}