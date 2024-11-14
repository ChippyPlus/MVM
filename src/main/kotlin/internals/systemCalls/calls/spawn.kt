package internals.systemCalls.calls

import data.registers.RegisterType
import engine.execution.Execute
import environment.reflection.VmTracked
import environment.reflection.reflection
import helpers.readRegisterString
import internals.Vm
import internals.systemCalls.SystemCall
import kotlinx.coroutines.*
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun SystemCall.spawn(pathX: RegisterType) {
	val newVm = Vm()

	val tracked = VmTracked(newVm)

	CoroutineScope(Dispatchers.IO).launch {
		tracked.thread = Thread.currentThread()
		reflection.vmTracker.add(tracked)
		Execute(newVm).execute(File(helpers.readRegisterString(pathX)))
//		println("Init -> ${Thread.currentThread().name}")
	}

}