package kernel

import engine.execution.Execute
import internals.Pc

class TaskManagerV2 {
	val keepPcs = mutableMapOf<KProcess, Pair<Pc, Execute>>()
	val deadProcess = mutableListOf<KProcess>()

	fun add(process: KProcess) = run { keepPcs[process] = Pair(process.vm.pcInternal, Execute(process)) }

	fun eventLoop() {
		if (keepPcs.isEmpty()) return
		outer@ while (true) {
			inner@ for (process in keepPcs) {
				if (deadProcess.toTypedArray().contentEquals(keepPcs.keys.toTypedArray())) break@outer
				if (process.key.instructionMemory[process.value.first.toInt()].name == "HALT" && process.key !in deadProcess) {
					deadProcess.add(process.key)
//					println("[DEAD] += | ${process.key.file.name} ")
				}

				if (process.key in deadProcess) {
//					println("[IGNORE DEAD] ${process.key.file.name} ")
					continue@inner
				}


				process.value.second.singleEvent(process.key.instructionMemory[process.value.first.toInt()])
//				println("[EVAL] ${process.key.file.name} | ${process.key.vm.pc} | ${process.key.currentInstruction} ")
			}
		}
	}


}