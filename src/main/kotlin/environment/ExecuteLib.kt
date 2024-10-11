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


	fun execute() {
		if (findMarLib(name) != null) {
			val file = File(findMarLib(name)!!)
			executeMar(file)
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

	private fun executeMar(file: File) {
		val oldPc = vm.pc
		val snapshot = snapShotRegisters()
		Execute().run(parser(file.readLines().subList(1, file.readLines().size)))
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

fun main() {
	val e = ExecuteLib("test")
	e.execute()
}