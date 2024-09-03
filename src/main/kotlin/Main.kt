package org.example

import org.example.data.registers.Registers
import org.example.helpers.Execute
import org.example.kvmInternals.classes.Kvm
import java.io.File


val register = Registers()
val systemRegisters = register.systemRegisters
val returnRegisters = register.returnRegisters
val generalRegisters = register.generalRegisters
val execute = Execute()
val f = File("src/main/resources/main.kar")
val kvm = Kvm()
val mov = kvm.dataTransfer
fun main() {
//    execute.execute(f)
//    execute.parser(f).forEach { println(it)}
    execute.execute(f)
}