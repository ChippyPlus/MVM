package kilb

import data.memory.MemoryAddress
import data.registers.RegisterType
import internalMemory
import registers
import vm

class Arrays {
	/**
	 * Like F1 = arrayRef
	 */
	fun size() {
		val meta = registers.read(RegisterType.F1).toLong()
		val count = internalMemory.read(MemoryAddress(meta + 1)).value!!
		vm.stackOperations.internalStack.push(count)
	}
}