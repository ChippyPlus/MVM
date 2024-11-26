package kilb

import data.registers.RegisterType
import internals.Vm

class Arrays(val vm: Vm) {
	fun size() {
		val meta = vm.registers.read(RegisterType.F1)
		val count = vm.internalMemory.read(meta + 1)
		vm.stackOperations.internalStack.push(count)
	}
}