package kilb

import data.registers.RegisterType
import helpers.readRegisterString
import vm

fun Klib.strtoint() = vm.stackOperations.internalStack.push(
	readRegisterString(RegisterType.F1).toLong()
)