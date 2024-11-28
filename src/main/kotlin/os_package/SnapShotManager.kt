package os_package

import data.registers.RegisterType
import internals.Vm

class SnapShotManager(val vm: Vm) {

	fun populateSnapShotRegister(snapShotRegisters: Map<RegisterType, Long>) {
		for (i in snapShotRegisters) {
			vm.registers.write(i.key, i.value)
		}
	}
	fun snapShotRegisters(): Map<RegisterType, Long> {
		val allRegisters = mutableMapOf<RegisterType, Long>()
		allRegisters.forEach { if (!it.key.name.startsWith('I')) allRegisters.remove(it.key) }
		for (i in RegisterType.entries) {
			allRegisters[i] = vm.registers.read(i)
		}
		return allRegisters
	}






}



