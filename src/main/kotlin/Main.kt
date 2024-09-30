import data.io.FileDescriptors
import data.memory.InternalMemory
import data.registers.Registers
import debugger.DebugEngine
import debugger.encoding.DebugFile
import engine.execution.Execute
import engine.parser
import engine.v2.Compile
import engine.v2.ExecutionV2
import environment.VMErrors
import internals.Vm
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.system.exitProcess

const val STACK_LIMIT = 32
const val MEMORY_LIMIT = 64
val vm = Vm()

val errors = VMErrors()
val fileDescriptors = FileDescriptors()
val register = Registers()
val internalMemory = InternalMemory()
val systemRegisters = register.systemRegisters
val returnRegisters = register.returnRegisters
val generalRegisters = register.generalRegisters
val execute = Execute()
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: mvm <command> [options]")
        exitProcess(1)
    }
    when (args[0]) {
        "run" -> {
            if (args.size < 2) {
                println("Usage: mvm run <file.kar>")
                exitProcess(1)
            }
            execute.execute(File(args[1]))
        }

        "crun" -> {
            ExecutionV2().execute(File(args[1]).readText())
        }

        "tokenise" -> {
            println(parser(File(args[1])))
        }

        "compile" -> {
            val out = Compile().execute(parser(File(args[1])))
            val f = File(args[1].split(".")[0] + ".machine")
            f.createNewFile()
            f.writeText(out)
        }

        "debug" -> {
            if (args.size < 3) {
                println("Usage: mvm debug <debugFile.json> <file.kar>")
                exitProcess(1)
            }
            val debugEngine = DebugEngine(Json.decodeFromString<DebugFile>(File(args[1]).readText()))
            execute.execute(File(args[2]), debugEngine)
        }

        else -> {
            println("Usage: mvm <command> <arguments...>")
            exitProcess(1)
        }
    }
}


