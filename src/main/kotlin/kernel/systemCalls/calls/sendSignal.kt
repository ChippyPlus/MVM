package kernel.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import kernel.interuptManager.InterruptManager
import kernel.systemCalls.SystemCall

fun SystemCall.sendSignal(code: RegisterType, process: RegisterType) {
	InterruptManager(vm).sendSignal(code.read(vm).toInt(), process.read(vm))
}