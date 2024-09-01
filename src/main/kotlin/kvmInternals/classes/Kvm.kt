package org.example.kvmInternals.classes

import kvmInternals.instructions.arithmetic.Arithmetic
import org.example.kvmInternals.instructions.dataTransfer.DataTransfer
import org.example.kvmInternals.instructions.logical.Logical

open class Kvm {
    val dataTransfer = DataTransfer()
    val arithmetic = Arithmetic()
    val logical = Logical()
    val stack = Stack()
    val controlFlow = ControlFlow()
    val memory = Memory()
    val systemCall = SystemCall()
}



