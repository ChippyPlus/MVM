package kernel

import data.registers.RegisterType
import data.registers.Registers
import kernel.process.KThread

class SnapShotManager(private val registers: Registers) {

	private val snapshots = mutableMapOf<KThread, Map<RegisterType, Long>>()

	fun populateSnapShotRegister(kThread: KThread) {
		for (i in snapshots[kThread]!!) {
			registers.write(i.key, i.value)
		}

	}

	fun snapShotRegisters(kThread: KThread) {
		val allRegisters = mutableMapOf<RegisterType, Long>()
		for (i in RegisterType.entries) {
			allRegisters[i] = registers.read(i)
		}
		snapshots[kThread] = allRegisters
	}

	fun initSnapShotRegister(kThread: KThread) {
		for (register in RegisterType.entries) {
			registers.write(register, 0L)
		}
		snapShotRegisters(kThread)

	}
}