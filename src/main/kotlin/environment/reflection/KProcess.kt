package environment.reflection

import engine.execution.InstructData
import internals.Vm

data class KProcess(val vm: Vm, var thread: Thread? = null) {
	val ipcPermissions = mutableListOf<Int>()
	var parent: Int? = null
	var instructionMemory = emptyList<InstructData>()
	val id: Int = reflection.vmTracker.size

	init {
		reflection.vmTracker.add(this)
	}


}
