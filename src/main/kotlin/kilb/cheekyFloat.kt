package kilb

import data.registers.RegisterType
import registers


// turns F1 into a renderable float
fun Klib.cheekyFloat() {
	println(registers.readFloat(RegisterType.F1))
}