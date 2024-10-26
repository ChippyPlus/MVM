package data.registers

import errors
import kotlin.system.exitProcess

class Registers {
	val registers = mutableMapOf<RegisterType, RegisterData>()

	init {
		for (register in RegisterType.entries) {

			if (register.name.startsWith('X')) {
				registers[register] = RegisterData(data = null, dataType = RegisterDataType.RFloat)
			} else {
				registers[register] = RegisterData(data = null, dataType = RegisterDataType.RLong)
			}
		}
	}

	fun readUnsafe(register: RegisterType): Long? = registers[register]!!.read()


	fun readFloatUnsafe(registerX: RegisterType): Float? {
//		if (registerX.name[0] != 'x') {
//			errors.InvalidRegisterTypeException("Not X register")
//		}
		return registers[registerX]!!.readFloat()
	}

	fun readFloat(registerX: RegisterType) = try {
		readFloatUnsafe(registerX)!!
	} catch (_: NullPointerException) {
		errors.NullRegisterException(registerX)
		exitProcess(1)
	}


	fun writeFloatUnsafe(registerX: RegisterType, valueX: Float?) {
//		if (registerX.name[0] != 'x') {
//			errors.InvalidRegisterTypeException("Not X register")
//		}
		registers[registerX]!!.writeFloat(valueX)
	}

	fun writeFloat(registerX: RegisterType, valueX: Float) = writeFloatUnsafe(registerX, valueX)


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