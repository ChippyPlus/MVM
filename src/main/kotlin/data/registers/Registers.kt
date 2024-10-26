package data.registers

import errors
import kotlin.system.exitProcess

class Registers {
	val registers = mutableMapOf<RegisterType, RegisterData>()

	init {
		for (register in RegisterType.entries) {


			registers[register] = RegisterData(
				name = register, data = null, dataType = if (register.name.startsWith('X')) {
					RegisterDataType.RFloat
				} else {
					RegisterDataType.RLong
				}
			)
		}
	}

	fun readUnsafe(register: RegisterType): Long? = registers[register]!!.read()


	fun readFloatUnsafe(registerX: RegisterType): Float {
		return registers[registerX]!!.readFloat()
	}

	fun readFloat(registerX: RegisterType) = try {
		readFloatUnsafe(registerX)
	} catch (_: NullPointerException) {
		errors.NullRegisterException(registerX)
		exitProcess(1)
	}

	fun readDoubleUnsafe(registerX: RegisterType): Double {
		return registers[registerX]!!.readDouble()
	}

	fun readDouble(registerX: RegisterType) = try {
		readDoubleUnsafe(registerX)
	} catch (_: NullPointerException) {
		errors.NullRegisterException(registerX)
		exitProcess(1)
	}


	fun writeX(registerX: RegisterType, value: Number) {
		if (registers[registerX]!!.dataType == RegisterDataType.RFloat) {
			writeFloatUnsafe(registerX, value as Float?)
		} else {
			writeDoubleUnsafe(registerX, value as Double?)
		}
	}

	fun readX(registerX: RegisterType): Number {
		return if (registers[registerX]!!.dataType == RegisterDataType.RFloat) {
			readFloatUnsafe(registerX)
		} else {
			readDoubleUnsafe(registerX)
		}
	}


	fun writeDoubleUnsafe(registerX: RegisterType, valueX: Double?) {
		registers[registerX]!!.writeDouble(valueX)
	}

	fun writeDouble(registerX: RegisterType, valueX: Double) = writeDoubleUnsafe(registerX, valueX)


	fun writeFloatUnsafe(registerX: RegisterType, valueX: Float?) {
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