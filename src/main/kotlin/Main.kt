
import data.io.FileDescriptors
import data.memory.InternalMemory
import data.registers.Registers
import engine.execution.Execute
import engine.parser
import environment.ExecuteLib
import environment.VMErrors
import helpers.Config
import internals.Vm
import optimisations.VarRedundancy
import java.io.File
import kotlin.system.exitProcess


val config = if (File("./config.json").exists()) Config(File("./config.json")) else null
val hertz = config?.hertz ?: 0L
val MEMORY_LIMIT = config?.memorySize ?: 256
val registers = Registers()
val vm = Vm()
val libExecute = ExecuteLib()
val errors = VMErrors()
val fileDescriptors = FileDescriptors()
val internalMemory = InternalMemory()

val execute = Execute()
fun main(args: Array<String>) {


	if (args.isEmpty()) {
		println("Usage: mvm <command> [options]")
		exitProcess(1)
	}
	when (args[0]) {
		"irun" -> {
			if (args.size < 2) {
				println("Usage: mvm irun <file.kar>")
				exitProcess(1)
			}
			execute.execute(File(args[1]))
		}


		"tokenise" -> {
			if (args.size < 2) {
				println("Usage: mvm tokenise <file.kar>")
				exitProcess(1)
			}
			parser(File(args[1]).readLines()).forEach(::println)
		}


		"otokenise" -> {
			if (args.size < 2) {
				println("Usage: mvm tokenise <file.kar>")
				exitProcess(1)
			}
			VarRedundancy(globalInfo = parser(File(args[1]).readLines())).cleanRedundancy().forEach(::println)
		}


		"help" -> {
			println(
				"mvm irun <file.kar> - Runs KAR code in interpreter mode with no optimisations, (This is the most stable mode)\n" + "mvm compile <file.kar> - Compiles and optimises the KAR code into the byte code stored in file.mar\n" + "mvm crun <file.mar> - Runs compiled code\n" + "mvm run <file.kar> - Compiles and runs code without creating a file\n" + "mvm tokenise <file.kar> - Shows the tokenised version of the code to the terminal\n" + "mvm otokenise <file.kar> - Shows the optimised tokenised version of the code to the terminal"
			)
		}

		else -> {
			println("Usage: mvm <command> <arguments...>")
			exitProcess(1)
		}
	}

	exitVM()
}


fun exitVM(): Nothing = exitProcess(0)


