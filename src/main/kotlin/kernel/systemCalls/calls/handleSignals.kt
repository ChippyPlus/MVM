package kernel.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import kernel.interuptManager.InterruptManager
import kernel.systemCalls.SystemCall

fun SystemCall.handleSignals(code: RegisterType, jumpWhere: RegisterType) {
	InterruptManager(vm).handleSystemCallRequest(code.read(vm).toInt(), jumpWhere.read(vm))
}