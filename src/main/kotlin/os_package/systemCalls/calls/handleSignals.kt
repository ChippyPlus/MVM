package os_package.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import os_package.interuptManager.InterruptManager
import os_package.systemCalls.SystemCall

fun SystemCall.handleSignals(code: RegisterType, jumpWhere: RegisterType) {
	InterruptManager(vm).handleSystemCallRequest(code.read(vm).toInt(), jumpWhere.read(vm))
}