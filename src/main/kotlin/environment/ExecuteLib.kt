package environment

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.Execute
import engine.parser
import errors
import helpers.fullRegisterReadUnsafe
import helpers.fullRegisterWriteUnsafe
import kilb.Klib
import vm
import java.io.File
import kotlin.system.exitProcess


class ExecuteLib(val name: String) {


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

	private val file = File(findMarLib(name) ?: run {
		errors.MissingLibraryException(name)
		exitProcess(1)
	})

	fun execute() {
		if (findMarLib(name) != null) {
			executeMar()
		} else {
			executeKt()
		}
	}

	private fun executeKt() {
		val oldPc = vm.pc
		val snapshot = snapShotRegisters()
		if (!Klib().match(name)) {
			errors.MissingLibraryException(name) // Kt should be the last resort
		}
		populateSnapShot(snapshot)
		vm.pc = oldPc
	}

	private fun executeMar() {
		val oldPc = vm.pc
		val snapshot = snapShotRegisters()
		Execute().run(parser(file.readLines().subList(1, file.readLines().size)))
		populateSnapShot(snapshot)
		vm.pc = oldPc
	}

	private fun snapShotRegisters(): MutableMap<SuperRegisterType, Long?> {
		val allRegisters = mutableMapOf<SuperRegisterType, Long?>()
		for (i in SuperRegisterType.entries) {
			allRegisters[i] = fullRegisterReadUnsafe(i)
		}
		return allRegisters
	}

	private fun populateSnapShot(snapShotRegisters: MutableMap<SuperRegisterType, Long?>) {
		for (i in snapShotRegisters) {
			fullRegisterWriteUnsafe(i.key, i.value)
		}
	}

}

fun main() {
	val e = ExecuteLib("test")
	e.execute()
}