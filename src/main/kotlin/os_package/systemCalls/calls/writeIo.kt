package os_package.systemCalls.calls

import data.registers.RegisterType
import os_package.systemCalls.SystemCall

fun SystemCall.writeIo(address: RegisterType) = call("writeIo") {
	var index: Int = 0
	while (true) {

		val byte = vm.heap!!.get(
			registers.read(register = address).plus(index)
		)
		if (byte.equals(0L)) {
			break
		}

		index++
		print(byte.toInt().toChar())

	}
//	println(internalMemory.memory)
}