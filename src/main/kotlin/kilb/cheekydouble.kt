package kilb

import data.registers.RegisterType


// turns F1 into a render able float
fun Klib.cheekyDouble() {
	println(Double.fromBits(kp.vm.registers.read(RegisterType.F1)))

}