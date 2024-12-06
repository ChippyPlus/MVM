package kilb

import data.registers.RegisterType


// turns F1 into a render able float
fun Klib.cheekyFloat() {
	println(Float.fromBits(vm.registers.read(RegisterType.F1).toInt()))
}