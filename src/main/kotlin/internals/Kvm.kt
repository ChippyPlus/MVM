package internals

import internals.instructions.arithmetic.Arithmetic
import internals.instructions.bitwise.Bitwise
import internals.instructions.dataTransfer.DataTransfer
import org.example.STACK_LIMIT
import internals.instructions.controlFlow.ControlFlow
import org.example.kvmInternals.instructions.ioAbstractions.IoAbstractions
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
    var pc = 0
}



