package org.example

import org.example.data.memory.InternalMemory
import org.example.data.registers.Registers
import org.example.environment.VMErrors
import org.example.helpers.Execute
import org.example.kvmInternals.classes.Kvm
import java.io.File


const val stackLimit = 16
const val memoryLimit = 32
val errors = VMErrors()
val register = Registers()
val internalMemory = InternalMemory()
val systemRegisters = register.systemRegisters
val returnRegisters = register.returnRegisters
val generalRegisters = register.generalRegisters
val execute = Execute()
val f = File("src/main/resources/main.kar")
val kvm = Kvm()
fun main() {
    execute.execute(f)
}