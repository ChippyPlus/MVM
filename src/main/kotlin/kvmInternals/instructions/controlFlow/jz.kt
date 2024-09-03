package org.example.kvmInternals.instructions.controlFlow

import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.fullRegisterRead
import org.example.kvm

fun ControlFlow.jz(targetAddress: Int, testRegister: SuperRegisterType) {
    if (fullRegisterRead(testRegister) == 0) {
        kvm.pc = targetAddress
    }

}