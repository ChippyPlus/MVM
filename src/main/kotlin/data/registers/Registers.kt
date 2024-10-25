package data.registers

import errors
import kotlin.system.exitProcess

class Registers {
	val registers = mutableMapOf<RegisterType, RegisterData>()

	init {
		for (register in RegisterType.entries) {
			registers[register] = RegisterData(data = null, dataType = RegisterDataType.RFloat)
		}
	}

	fun readUnsafe(register: RegisterType): Double? = registers[register]?.read()

	fun read(register: RegisterType): Double = try {
		val value = registers[register]!!.read()!!

		/**
		 * This code isn't possible because we can't detect if an overflow occurs or not in the first place
		 */
//		if (value > Double.MAX_VALUE) {
//			write(intelNames[IntelRegisters.ESF], ErrorType.Double_OVERFLOW.code)
//		} else if (value < Double.MIN_VALUE) {
//			write(intelNames[IntelRegisters.ESF], ErrorType.Double_UNDERFLOW.code)
//		}


		value

	} catch (_: NullPointerException) {
		errors.NullRegisterException(register)
		exitProcess(9)
	}


	fun readData(register: RegisterType) = registers[register]!!

	fun write(register: RegisterType, value: Double) {
		registers[register]!!.write(value)
	}

	fun write(register: RegisterType, value: Long?) {
		registers[register]!!.write(value?.toDouble())
	}

	fun writeUnsafe(register: RegisterType, value: Double?) {
		registers[register]!!.write(value)
	}

}