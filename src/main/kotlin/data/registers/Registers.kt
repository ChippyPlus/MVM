package data.registers

import errors
import kotlin.system.exitProcess

class Registers {
	val registers = mutableMapOf<RegisterType, RegisterData>()

	init {
		for (register in RegisterType.entries) {
			registers[register] = RegisterData(data = null, dataType = RegisterDataType.RLong)
		}
	}

	fun readUnsafe(register: RegisterType): Long? = registers[register]!!.read()

	fun read(register: RegisterType): Long = try {
		val value = registers[register]!!.read()
		value!!

	} catch (_: NullPointerException) {
		errors.NullRegisterException(register)
		exitProcess(1)
	}

	fun write(register: RegisterType, value: Long) {
		registers[register]!!.write(value)
	}

	fun writeUnsafe(register: RegisterType, value: Long?) {
		registers[register]!!.write(value)
	}

}