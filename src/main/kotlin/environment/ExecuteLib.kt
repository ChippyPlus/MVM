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


class ExecuteLib(val name: String) {


	private fun findMarLib(name: String) {
		if ('.' in name) {
			println(name.split("."))
			return
		}
		println(name)
	}

	private val file = File("${vm.functions.stdlibPath}/$name.lib")

	fun execute() {
		findMarLib(name)
		return
		if (!file.exists()) {
			executeKt()
		} else {
			executeMar()
		}
	}

	private fun executeKt() {
		val oldPc = vm.pc
		val snapshot = snapShotRegisters()
		if (!Klib().match(name)) {
			errors.MissingLibraryException(name)
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