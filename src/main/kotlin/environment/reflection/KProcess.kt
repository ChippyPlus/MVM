package environment.reflection

import data.registers.Registers
import engine.execution.InstructData
import internals.Vm

data class KProcess(val vm: Vm, var thread: Thread? = null) {

	val registers = Registers()
	val ipcPermissions = mutableListOf<Int>()
	var parent: Int? = null
	var instructionMemory = emptyList<InstructData>()
	val id: Int = reflection.vmTracker.size

	init {
		reflection.vmTracker.add(this)
	}


}
