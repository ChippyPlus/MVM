package internals.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import environment.interuptManager.InterruptManager
import internals.systemCalls.SystemCall

fun SystemCall.sendSignal(code: RegisterType, process: RegisterType) {
	InterruptManager(vm).sendSignal(code.read(vm).toInt(), process.read(vm))
}