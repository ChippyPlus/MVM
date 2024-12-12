package kernel.process

import data.memory.ProcessMemory
import engine.execution.InstructData
import environment.reflection.reflection
import helpers.RuntimeStates
import internals.debug.Debug
import os
import java.io.File

data class KProcess(var file: File) {
	val debug = Debug(this)
	var runtimeState = RuntimeStates.RUNNING
	val addressSpace = ProcessMemory(this)
	var currentInstruction: InstructData = InstructData(name = "init", arrayOf())
	val threads = mutableListOf<KThread>()
	private val mainThread = KThread(
		threads.size.toUInt(), this
	)

	init {
		threads.add(mainThread)
		reflection.vmTracker.add(this)
	}


	val ipcPermissions = mutableListOf<Int>()
	var parent: Int? = null
	var instructionMemory = emptyList<InstructData>()
	val id: Int = reflection.vmTracker.size


	suspend fun notifyOS() = os.taskManager.add(this)
}