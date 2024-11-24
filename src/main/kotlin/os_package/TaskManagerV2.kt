package os_package

import engine.execution.Execute
import internals.Pc

class TaskManagerV2 {
	val keepPcs = mutableMapOf<KProcess, Pair<Pc, Execute>>()

	fun add(process: KProcess) = run { keepPcs[process] = Pair(process.vm.pcInternal, Execute(process)) }

	fun see() = println(keepPcs)


	fun eventLoop() {
		while (true) {
			for (process in keepPcs) {
				if (process.key.instructionMemory[process.value.first.toInt()].name == "HALT") {
					keepPcs.remove(process.key)
				}
				process.value.second.singleEvent(process.key.instructionMemory[process.value.first.toInt()])
			}
		}
	}


}