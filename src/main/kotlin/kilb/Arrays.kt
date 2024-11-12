package kilb

import data.memory.MemoryAddress
import data.registers.RegisterType
import internals.Vm

class Arrays(val vm: Vm) {

	/**
	 * Like F1 = arrayRef
	 */
	fun size() {
		val meta = vm.registers.read(RegisterType.F1)
		val count = vm.internalMemory.read(MemoryAddress(meta + 1)).value!!
		vm.stackOperations.internalStack.push(count)
	}
}