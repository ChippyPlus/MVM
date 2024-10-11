package kilb

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType
import helpers.registerRead
import internalMemory
import vm

class Arrays {
	/**
	 * Like F1 = arrayRef
	 */
	fun size() {
		val meta = registerRead(SuperRegisterType.F1)
		val count = internalMemory.read(MemoryAddress(meta + 1)).value!!
		vm.stackOperations.internalStack.push(count)
	}
}