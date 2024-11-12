package internals.systemCalls.calls

import data.registers.RegisterType
import engine.execution.Execute
import helpers.readRegisterString
import internals.Vm
import internals.systemCalls.SystemCall
import java.io.File

fun SystemCall.spawn(pathX: RegisterType) {
	val newVm = Vm()
	Execute(newVm).execute(File(helpers.readRegisterString(pathX)))
}