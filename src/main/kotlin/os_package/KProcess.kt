package os_package

import engine.execution.InstructData
import environment.reflection.reflection
import internals.Vm
import taskManager
import java.io.File

data class KProcess(val vm: Vm, val file: File) {

	//	val registers = Registers()
	val ipcPermissions = mutableListOf<Int>()
	var parent: Int? = null
	var instructionMemory = emptyList<InstructData>()
	val id: Int = reflection.vmTracker.size
	val thread: Thread = Thread.currentThread()

	init {
		taskManager.add(this)
		reflection.vmTracker.add(this)
	}
}