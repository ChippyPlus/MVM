package kernel

import data.registers.RegisterType
import data.registers.Registers
import kernel.process.KProcess

class SnapShotManager(private val registers: Registers) {

	private val snapshots = mutableMapOf<KProcess, Map<RegisterType, Long>>()

	fun populateSnapShotRegister(kProcess: KProcess) {
		for (i in snapshots[kProcess]!!) {
			registers.write(i.key, i.value)
		}

	}

	fun snapShotRegisters(kProcess: KProcess) {
		val allRegisters = mutableMapOf<RegisterType, Long>()
		for (i in RegisterType.entries) {
			allRegisters[i] = registers.read(i)
		}
		snapshots[kProcess] = allRegisters
	}

	fun initSnapShotRegister(kProcess: KProcess) {
		for (register in RegisterType.entries) {
			registers.write(register, 0L)
		}
		snapShotRegisters(kProcess)

	}
}