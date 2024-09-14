package org.example

import debugger.DebugEngine
import kotlinx.serialization.json.Json
import org.example.data.io.FileDescriptors
import org.example.data.memory.InternalMemory
import org.example.data.registers.Registers
import org.example.engine.execution.Execute
import org.example.environment.VMErrors
import org.example.internals.encoding.DebugFile
import org.example.kvmInternals.Kvm
import java.io.File



const val STACK_LIMIT = 32
const val MEMORY_LIMIT = 64
val kvm = Kvm()
val debugEngine = DebugEngine(Json.decodeFromString<DebugFile>(File("src/main/resources/debug/debug.json").readText()))
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
}