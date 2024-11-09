package kilb

import data.registers.RegisterType
import registers


// turns F1 into a renderable float
fun Klib.cheekyDouble() {
	println(Double.fromBits(registers.readX(RegisterType.F1).value))

}