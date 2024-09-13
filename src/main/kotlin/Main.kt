package org.example

import kotlinx.serialization.json.Json
import org.example.data.io.FileDescriptors
import org.example.data.memory.InternalMemory
import org.example.data.registers.Registers
import org.example.debuger.internals.DebugEngine
import org.example.debuger.jsonInfo.DebugFile
import org.example.engine.execution.Execute
import org.example.environment.VMErrors
import org.example.kvmInternals.classes.Kvm
import java.io.File


const val STACK_LIMIT = 32
const val MEMORY_LIMIT = 1024
val kvm = Kvm()
val debugEngine= DebugEngine(Json.decodeFromString<DebugFile>(File("src/main/resources/debug/debug.json").readText()))
val errors = VMErrors()
val fileDescriptors = FileDescriptors()
val register = Registers()
val internalMemory = InternalMemory()
val systemRegisters = register.systemRegisters
val returnRegisters = register.returnRegisters
val generalRegisters = register.generalRegisters
val execute = Execute()
val f = File("src/main/resources/main.kar")
fun main() {
    execute.execute(f)
//    println(internalMemory.memory)
}