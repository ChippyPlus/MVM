package org.example.kvmInternals.classes

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.kvmInternals.instructions.controlFlow.ControlFlow
import org.example.kvmInternals.instructions.dataTransfer.DataTransfer
import org.example.kvmInternals.instructions.ioAbstractions.IoAbstractions
import kvmInternals.instructions.bitwise.Bitwise
import org.example.kvmInternals.instructions.memory.Memory
import org.example.kvmInternals.instructions.stackOperations.StackOperations
import org.example.STACK_LIMIT

open class Kvm {
    val dataTransfer = DataTransfer()
    val arithmetic = Arithmetic()
    val bitwise = Bitwise()
    val stackOperations = StackOperations(STACK_LIMIT)
    val controlFlow = ControlFlow()
    val memory = Memory()
    val systemCall = SystemCall()
    val ioAbstractions = IoAbstractions()
    var pc = 0
}



