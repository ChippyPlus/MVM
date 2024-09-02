package org.example.kvmInternals.classes

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.kvmInternals.instructions.dataTransfer.DataTransfer
import org.example.kvmInternals.instructions.ioAbstractions.IoAbstractions
import org.example.kvmInternals.instructions.logical.Logical
import org.example.kvmInternals.instructions.stackOperations.StackOperations

open class Kvm {
    val dataTransfer = DataTransfer()
    val arithmetic = Arithmetic()
    val logical = Logical()
    val stackOperations = StackOperations()
    val controlFlow = ControlFlow()
    val memory = Memory()
    val systemCall = SystemCall()
    val ioAbstractions = IoAbstractions()
    var pc = 1
}



