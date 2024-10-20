package internals.systemCalls.calls

import data.memory.MemoryAddress
import data.memory.MemoryValue
import data.registers.RegisterType
import internalMemory
import internals.systemCalls.SystemCall
import registers

/**
 * Writes a null-terminated string to the console.
 *
 * System call number: 24
 *
 * @param address The register containing the memory address of the start of the string (stored in register S1).
 */
fun SystemCall.writeIo(address: RegisterType) = call("writeIo") {
	var index: Int = 0
	while (true) {

		val byte: MemoryValue = internalMemory.read(
			address = MemoryAddress(address = registers.read(register = address).plus(index))
		)
		if (byte.value!!.equals(0L)) {
			break
		}

		index++
		print(byte.value.toInt().toChar())

	}
}