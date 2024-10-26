package internals.instructions.xFloats

import data.registers.RegisterType
import registers

fun XFloats.xLit(registerX: RegisterType, valueX: Float) {

	registers.writeFloat(registerX, valueX)
}