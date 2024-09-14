package org.example.kvmInternals.instructions.ioAbstractions

import  org.example.kvm

fun IoAbstractions.prints() {
    println(kvm.stackOperations.internalStack.peek())
}