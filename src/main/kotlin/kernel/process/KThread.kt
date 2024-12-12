package kernel.process

import engine.execution.InstructData
import helpers.RuntimeStates
import internals.Vm
import kernel.ExecuteLib
import os

data class KThread(val id: UInt, val parent: KProcess) {
	var currentInstruction: InstructData = InstructData(name = "init", arrayOf())
	var state: RuntimeStates = RuntimeStates.RUNNING
	val vm = Vm()

	init {
		vm.heap = parent.addressSpace.heap
		vm.libExecute = ExecuteLib(vm)
		os.snapShotManager.initSnapShotRegister(this)
	}
}