import data.io.FileDescriptors
import data.memory.InternalMemory
import data.registers.Registers
import debugger.DebugEngine
import debugger.encoding.DebugFile
import engine.execution.Execute
import engine.parser
import environment.VMErrors
import internals.Kvm
import kotlinx.serialization.json.Json
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
//    execute.execute(f)
	execute.parser(f)

}