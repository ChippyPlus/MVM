package kilb

import data.registers.RegisterType
import helpers.readRegisterString

fun Klib.strtoint() = vm.stackOperations.internalStack.push(
	vm.helpers.readRegisterString(RegisterType.F1).toLong()
)