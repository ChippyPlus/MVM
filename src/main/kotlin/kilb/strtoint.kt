package kilb

import data.registers.enumIdenifiers.SuperRegisterType
import helpers.readRegisterString
import vm

fun Klib.strtoint() = vm.stackOperations.internalStack.push(
	readRegisterString(SuperRegisterType.F1).toLong()
)