package internals.systemCalls.calls

import data.registers.RegisterType
import engine.execution.Execute
import environment.reflection.VmTracked
import environment.reflection.reflection
import helpers.readRegisterString
import internals.Vm
import internals.systemCalls.SystemCall
import taskManager
import java.io.File

fun SystemCall.spawn(pathX: RegisterType) {
	val path = helpers.readRegisterString(pathX)
	val newVm = Vm()
	val tracked = VmTracked(newVm)
	tracked.thread = Thread.currentThread()
	reflection.vmTracker.add(tracked)
	taskManager.addTask {
		Execute(newVm).execute(File(path))
	}
	registers.write(RegisterType.R2, tracked.id.toLong())
}


fun SystemCall.send_t(code: RegisterType, wanted_p: RegisterType = RegisterType.I10) {
	val x = reflection.vmTracker.groupBy(VmTracked::id)
	x[registers.read(code).toInt()]!![0].vm.registers.write(RegisterType.I10, registers.read(wanted_p))
}

fun SystemCall.share_m(vm_id: RegisterType, fromX: RegisterType, toX: RegisterType) {
	val x = reflection.vmTracker.groupBy(VmTracked::id)
	val vmI = x[registers.read(vm_id).toInt()]!![0].vm
	val from = registers.read(fromX)
	val to = registers.read(toX)

	vmI.internalMemory.link(vm.internalMemory, from..to)

}