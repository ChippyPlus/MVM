package internals.systemCalls.calls

import data.registers.RegisterType
import internals.systemCalls.SystemCall

fun SystemCall.writeIo(address: RegisterType) = call("writeIo") {
	var index: Int = 0
	while (true) {

		val byte = internalMemory.read(
			address = registers.read(register = address).plus(index)
		)
		if (byte.equals(0L)) {
			break
		}

		index++
		print(byte.toInt().toChar())

	}
//	println(internalMemory.memory)
}