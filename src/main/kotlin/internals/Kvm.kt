package internals

import STACK_LIMIT
import internals.instructions.arithmetic.Arithmetic
import internals.instructions.bitwise.Bitwise
import internals.instructions.controlFlow.ControlFlow
import internals.instructions.dataTransfer.DataTransfer
import internals.instructions.floats.Floats
import internals.instructions.ioAbstractions.IoAbstractions
import internals.instructions.memory.Memory
import internals.instructions.stackOperations.StackOperations
import internals.instructions.strings.Strings
import internals.systemCalls.SystemCall

open class Kvm {
    val dataTransfer = DataTransfer()
    val arithmetic = Arithmetic()
    val bitwise = Bitwise()
    val stackOperations = StackOperations(STACK_LIMIT)
    val controlFlow = ControlFlow()
    val memory = Memory()
    val systemCall = SystemCall()
    val ioAbstractions = IoAbstractions()
    val strings = Strings()
    val floats = Floats()
    var pc = 0
}



