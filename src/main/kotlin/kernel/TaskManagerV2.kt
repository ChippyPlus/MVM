package kernel

import engine.execution.Execute
import internals.Pc
import kernel.process.KProcess
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import os

class TaskManagerV2 {
	private val mutex = Mutex()
	val keepPcs = mutableMapOf<KProcess, Pair<Pc, Execute>>()
	val deadProcess = mutableListOf<KProcess>()

	suspend fun add(process: KProcess) {
		mutex.withLock {
			keepPcs[process] = Pair(process.vm.pcInternal, Execute(process))
		}
	}

	suspend fun eventLoop() {
		if (keepPcs.isEmpty()) return
		outer@ while (true) {
			val keysCopy = keepPcs.toMap()
			inner@ for (process in keysCopy) {
				val kp = process.key
				if (deadProcess.toTypedArray().contentEquals(keysCopy.keys.toTypedArray())) break@outer
				if (process.key.instructionMemory[process.value.first.toInt()].name == "HALT" && process.key !in deadProcess) {
					deadProcess.add(process.key)
				} else if (process.key in deadProcess) {
					continue@inner
				} else {
					os.snapShotManager.populateSnapShotRegister(kp)
					process.value.second.singleEvent(process.key.instructionMemory[process.value.first.toInt()])
					os.snapShotManager.snapShotRegisters(kp)
				}
			}
		}
	}

	suspend fun eventLoopQ() {
		if (keepPcs.isEmpty()) return

	}


}