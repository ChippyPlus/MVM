package org.example

import org.example.data.registers.Registers
import org.example.data.registers.enumIdenifiers.GeneralRegisterType
import org.example.data.registers.enumIdenifiers.SuperRegisterType
import org.example.helpers.RegisterAllMap
import org.example.kvmInternals.classes.Kvm
import org.example.kvmInternals.instructions.dataTransfer.mov


val register = Registers()
val systemRegisters = register.systemRegisters
val returnRegisters = register.returnRegisters
val generalRegisters = register.generalRegisters

val kvm = Kvm()
val mov = kvm.dataTransfer
fun main() {
    generalRegisters
    generalRegisters.write(GeneralRegisterType.G4,24u)
    mov.mov(SuperRegisterType.G4,SuperRegisterType.G1)
    println(RegisterAllMap()[SuperRegisterType.G1])
}