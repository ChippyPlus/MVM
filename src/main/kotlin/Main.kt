import engine.execution.Execute
import engine.parser
import environment.TaskManager
import environment.VMErrors
import environment.reflection.KProcess
import environment.reflection.reflection
import helpers.Config
import internals.Vm
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import os_package.OS
import java.io.File
import kotlin.system.exitProcess


val config = if (File("./config.json").exists()) Config(File("./config.json")) else null
val hertz = config?.hertz ?: 0L
val MEMORY_LIMIT = config?.memorySize ?: 256
val errors = VMErrors()
val os = OS()
val taskManager = TaskManager()
val initVm = Vm()

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
fun main(args: Array<String>): Unit = runBlocking(newSingleThreadContext("Kotlin's main")) {
	val init = KProcess(initVm)
	init.thread = Thread.currentThread()

	val execute = Execute(kp = init, File(args[1]))


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
			reflection.currentFileData.name = args[1]

			println(init.instructionMemory)
			execute.execute()
		}


		"tokenise" -> {
			if (args.size < 2) {
				println("Usage: mvm tokenise <file.kar>")
				exitProcess(1)
			}
			parser(init, File(args[1]).readLines())
			init.instructionMemory.forEach(::println)
		}


		"otokenise" -> {
			if (args.size < 2) {
				println("Usage: mvm tokenise <file.kar>")
				exitProcess(1)
			}
			println("Deprecated!")
//			VarRedundancy(globalInfo = parser(init, File(args[1]).readLines())).cleanRedundancy().forEach(::println)
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

	taskManager.wait()

}



