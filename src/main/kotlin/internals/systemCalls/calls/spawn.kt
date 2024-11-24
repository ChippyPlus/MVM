package internals.systemCalls.calls

import data.registers.RegisterType
import engine.execution.Execute
import environment.reflection.KProcess
import environment.reflection.reflection
import helpers.RuntimeStates
import helpers.readRegisterString
import internals.Vm
import internals.systemCalls.SystemCall
import taskManager
import java.io.File

fun SystemCall.spawn(pathX: RegisterType) {
	val path = helpers.readRegisterString(pathX)
	val newVm = Vm()
	val tracked = KProcess(newVm)
	tracked.thread = Thread.currentThread()
	taskManager.addTask {
		tracked.parent = reflection.groupTrackedVmByVm()[vm]!!.id
		Execute(tracked, File(path)).execute()
	}
	registers.write(RegisterType.R2, tracked.id.toLong())
}


fun SystemCall.share_m(vm_id: RegisterType, fromX: RegisterType, toX: RegisterType) {
	val x = reflection.vmTracker.groupBy(KProcess::id)
	val vmI = x[registers.read(vm_id).toInt()]!![0].vm
	val from = registers.read(fromX)
	val to = registers.read(toX)

	vmI.internalMemory.link(vm.internalMemory, from..to)
}


fun SystemCall.pause_t(vmToPause: RegisterType) {
	val x = reflection.vmTracker.groupBy(KProcess::id)
	x[registers.read(vmToPause).toInt()]!![0].vm.runtimeState = RuntimeStates.PAUSED
}

fun SystemCall.continue_t(vmToPause: RegisterType) {
	val x = reflection.vmTracker.groupBy(KProcess::id)
	x[registers.read(vmToPause).toInt()]!![0].vm.runtimeState = RuntimeStates.RUNNING
}