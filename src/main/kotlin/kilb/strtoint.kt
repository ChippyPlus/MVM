package kilb

import data.registers.RegisterType
import helpers.readRegisterString

fun Klib.strtoint() = kp.vm.stackOperations.internalStack.push(
	kp.vm.helpers.readRegisterString(RegisterType.F1).toLong()
)