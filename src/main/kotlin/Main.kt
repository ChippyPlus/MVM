package org.example

import org.example.data.io.FileDescriptors
import org.example.data.memory.InternalMemory
import org.example.data.registers.Registers
import org.example.engine.execution.Execute
import org.example.environment.VMErrors
import org.example.kvmInternals.classes.Kvm
import java.io.File


const val STACK_LIMIT = 32
const val MEMORY_LIMIT = 1024
val errors = VMErrors()
val fileDescriptors = FileDescriptors()
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