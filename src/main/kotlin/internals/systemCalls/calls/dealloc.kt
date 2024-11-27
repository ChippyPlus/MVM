package internals.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import internals.systemCalls.SystemCall

fun SystemCall.dealloc(pointer: RegisterType) = kp.addressSpace.heap.dealloc(pointer.read(vm))
