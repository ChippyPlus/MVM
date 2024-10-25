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

	fun readUnsafe(register: RegisterType): Long? = registers[register]?.read()

	fun read(register: RegisterType): Long = try {
		val value = registers[register]!!.read()!!

		/**
		 * This code isn't possible because we can't detect if an overflow occurs or not in the first place
		 */
//		if (value > Long.MAX_VALUE) {
//			write(intelNames[IntelRegisters.ESF], ErrorType.LONG_OVERFLOW.code)
//		} else if (value < Long.MIN_VALUE) {
//			write(intelNames[IntelRegisters.ESF], ErrorType.LONG_UNDERFLOW.code)
//		}




		value

	} catch (_: NullPointerException) {
		errors.NullRegisterException(register)
		exitProcess(9)
	}


	fun readData(register: RegisterType) = registers[register]!!

	fun write(register: RegisterType, value: Long) {
		registers[register]!!.write(value)
	}

	fun writeUnsafe(register: RegisterType, value: Long?) {
		registers[register]!!.write(value)
	}

}