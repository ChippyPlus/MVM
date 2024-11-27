package os_package.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import os_package.systemCalls.SystemCall

fun SystemCall.dealloc(pointer: RegisterType) = kp.addressSpace.heap.dealloc(pointer.read(vm))
