package internals.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import environment.interuptManager.InterruptManager
import internals.systemCalls.SystemCall

fun SystemCall.sendSignal(code: RegisterType, process: RegisterType) {
	InterruptManager().sendSignal(code.read().toInt(), process.read())
}