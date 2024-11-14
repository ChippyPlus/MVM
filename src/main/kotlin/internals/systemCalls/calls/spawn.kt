package internals.systemCalls.calls

import data.registers.RegisterType
import engine.execution.Execute
import environment.reflection.VmTracked
import helpers.readRegisterString
import internals.Vm
import internals.systemCalls.SystemCall
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import taskManager
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun SystemCall.spawn(pathX: RegisterType) {
	val newVm = Vm()

	val tracked = VmTracked(newVm)

	tracked.thread = Thread.currentThread()



	taskManager.addTask {
		Execute(newVm).execute(File(helpers.readRegisterString(pathX)))
	}


}