package internals.systemCalls.calls

import data.registers.RegisterType
import engine.execution.Execute
import environment.reflection.VmTracked
import environment.reflection.reflection
import helpers.readRegisterString
import internals.Vm
import internals.systemCalls.SystemCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.coroutineContext

fun SystemCall.spawn(pathX: RegisterType) {
	val newVm = Vm()
	reflection.vmTracker.add(VmTracked(newVm))

	CoroutineScope(coroutineContext).launch {
		Execute(newVm).execute(File(helpers.readRegisterString(pathX)))
	}
}