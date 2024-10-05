package internals

import config
import internals.instructions.arithmetic.Arithmetic
import internals.instructions.bitwise.Bitwise
import internals.instructions.controlFlow.ControlFlow
import internals.instructions.dataTransfer.DataTransfer
import internals.instructions.ioAbstractions.IoAbstractions
import internals.instructions.memory.Memory
import internals.instructions.stackOperations.StackOperations
import internals.instructions.strings.Strings
import internals.systemCalls.SystemCall

open class Vm {
    val dataTransfer = DataTransfer()
    val arithmetic = Arithmetic()
    val bitwise = Bitwise()
    val stackOperations = StackOperations(config?.stackSize ?: 12)
    val controlFlow = ControlFlow()
    val memory = Memory()
    val systemCall = SystemCall()
    val ioAbstractions = IoAbstractions()
    val strings = Strings()
    var pc = 0
}



