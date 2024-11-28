package os_package.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import os_package.interuptManager.InterruptManager
import os_package.systemCalls.SystemCall

fun SystemCall.sendSignal(code: RegisterType, process: RegisterType) {
	InterruptManager(vm).sendSignal(code.read(vm).toInt(), process.read(vm))
}