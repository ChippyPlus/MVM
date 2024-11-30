package kernel.systemCalls.calls

import data.registers.RegisterType
import environment.reflection.reflection
import helpers.RuntimeStates
import kernel.KProcess
import kernel.systemCalls.SystemCall


fun SystemCall.share_m(vm_id: RegisterType, fromX: RegisterType, toX: RegisterType) {
	val x = reflection.vmTracker.groupBy(KProcess::id)
	val vmI = x[registers.read(vm_id).toInt()]!![0].vm
	val from = registers.read(fromX)
	val to = registers.read(toX)

//	vmI.internalMemory.link(vm.internalMemory, from..to)
	// TODO New linking API
}


fun SystemCall.pause_t(vmToPause: RegisterType) {
	val x = reflection.vmTracker.groupBy(KProcess::id)
	x[registers.read(vmToPause).toInt()]!![0].runtimeState = RuntimeStates.PAUSED
}

fun SystemCall.continue_t(vmToPause: RegisterType) {
	val x = reflection.vmTracker.groupBy(KProcess::id)
	x[registers.read(vmToPause).toInt()]!![0].runtimeState = RuntimeStates.RUNNING
}