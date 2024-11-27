package internals.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import data.registers.write
import internals.systemCalls.SystemCall

fun SystemCall.allocate(size: RegisterType) {
	val pointer = kp.addressSpace.heap.alloc(size.read(vm).toInt())
	RegisterType.R2.write(vm, pointer)
}