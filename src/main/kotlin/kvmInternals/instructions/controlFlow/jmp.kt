package org.example.kvmInternals.instructions.controlFlow

import org.example.kvm

fun ControlFlow.jmp(targetAddress: Int) {
    kvm.pc = targetAddress

}