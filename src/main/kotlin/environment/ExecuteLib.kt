package environment

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.Execute
import engine.parser
import errors
import helpers.registerReadUnsafe
import helpers.registerWriteUnsafe
import kilb.Klib
import vm
import java.io.File


class ExecuteLib {
	var currentFunction = ""
	var enabledFunction = false
	private fun findMarLib(name: String): String? {
		if (File("${vm.functions.stdlibPath}/$name.lib").exists()) {
			val path = File("${vm.functions.stdlibPath}/$name.lib").absolutePath
			return path
		}

		if ('.' in name && File( // use FILE
				"${vm.functions.stdlibPath}/${name.split('.')[0]}/${
					name.split(
						'.'
					)[1]
				}.lib"
			).exists()
		) {
			val path = File(
				"${vm.functions.stdlibPath}/${name.split('.')[0]}/${
					name.split(
						'.'
					)[1]
				}.lib"
			).absolutePath
			return path
		}
		return null
	}


	fun execute(name: String) {
		if (findMarLib(name) != null) {
			val file = File(findMarLib(name)!!)
			enabledFunction = true
			currentFunction = File(findMarLib(name)!!).name
			executeMar(file)
			enabledFunction = false

		} else {
			executeKt(name)
		}
	}

	private fun executeKt(name: String) {
		val oldPc = vm.pc
		val snapshot = snapShotRegisters()
		if (!Klib().match(name)) {
			errors.MissingLibraryException(name) // Kt should be the last resort
		}
		currentFunction = name
		populateSnapShot(snapshot)
		vm.pc = oldPc
	}

	private fun executeMar(file: File) {
		val oldPc = vm.pc
		val snapshot = snapShotRegisters()
		Execute().run(parser(file.readLines().subList(1, file.readLines().size)), usedByLib = true)
		populateSnapShot(snapshot)
		vm.pc = oldPc
	}

	private fun snapShotRegisters(): MutableMap<SuperRegisterType, Long?> {
		val allRegisters = mutableMapOf<SuperRegisterType, Long?>()
		for (i in SuperRegisterType.entries) {
			allRegisters[i] = registerReadUnsafe(i)
		}
		return allRegisters
	}

	private fun populateSnapShot(snapShotRegisters: MutableMap<SuperRegisterType, Long?>) {
		for (i in snapShotRegisters) {
			registerWriteUnsafe(i.key, i.value)
		}
	}

}


