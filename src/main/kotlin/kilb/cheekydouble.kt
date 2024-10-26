package kilb

import data.registers.RegisterType
import registers


// turns F1 into a renderable float
fun Klib.cheekyDouble() {
	println(registers.readFloat(RegisterType.F1))
}