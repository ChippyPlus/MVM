package org.example.kvmInternals

import internals.instructions.arithmetic.Arithmetic
import internals.instructions.bitwise.Bitwise
import org.example.STACK_LIMIT
import org.example.kvmInternals.instructions.controlFlow.ControlFlow
import internals.instructions.dataTransfer.DataTransfer
import org.example.kvmInternals.instructions.ioAbstractions.IoAbstractions
import org.example.kvmInternals.instructions.memory.Memory
import org.example.kvmInternals.instructions.stackOperations.StackOperations
import org.example.kvmInternals.instructions.strings.Strings
import org.example.kvmInternals.systemCalls.SystemCall

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



