package data.registers

import errors
import kotlin.system.exitProcess

class Registers {
	val registers = mutableMapOf<RegisterType, Long?>()

	init {
		for (register in RegisterType.entries) {
			registers[register] = null
		}
	}

	fun readUnsafe(register: RegisterType): Long? = registers[register]

	fun read(register: RegisterType): Long = try {
		registers[register]!!
	} catch (_: NullPointerException) {
		errors.NullRegisterException(register)
		exitProcess(9)
	}

	fun write(register: RegisterType, value: Long) {
		registers[register] = value
	}

	fun writeUnsafe(register: RegisterType, value: Long?) {
		registers[register] = value
	}

}