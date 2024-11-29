package kernel

import data.memory.ProcessMemory
import engine.execution.InstructData
import environment.reflection.reflection
import internals.Vm
import internals.debug.DebugFullSnapShots
import os
import java.io.File

data class KProcess(val vm: Vm, var file: File) {
	val debugFullSnapShots = DebugFullSnapShots()
	val addressSpace = ProcessMemory(this)

	init {
		reflection.vmTracker.add(this)
		vm.heap = addressSpace.heap
		vm.libExecute = ExecuteLib(vm)
	}


//	val registers = Registers(vm) // PLEASE COME BACK!


	val ipcPermissions = mutableListOf<Int>()
	var parent: Int? = null
	var instructionMemory = emptyList<InstructData>()
	val id: Int = reflection.vmTracker.size
	val thread: Thread = Thread.currentThread()


	fun notifyOS() = os.taskManager.add(this)
}