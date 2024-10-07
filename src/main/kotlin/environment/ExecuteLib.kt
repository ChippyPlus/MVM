package environment

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.Execute
import engine.parser
import helpers.fullRegisterReadUnsafe
import helpers.fullRegisterWriteUnsafe
import vm
import java.io.File


class ExecuteLib(name: String) {
	private val file = File("./lib/$name.lib")

	fun execute() {
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