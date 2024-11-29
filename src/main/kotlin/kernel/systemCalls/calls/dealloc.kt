package kernel.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import kernel.systemCalls.SystemCall

fun SystemCall.dealloc(pointer: RegisterType) = kp.addressSpace.heap.dealloc(pointer.read(vm))
