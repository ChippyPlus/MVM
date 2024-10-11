package data.registers

import data.registers.enumIdenifiers.FunctionRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess

/**
 * Represents the set of function argument registers in the virtual machine.
 *
 * Function Registers registers are used as arguments in function calls that are user defined or builtin
 */
class FunctionRegisters {
	var f0: Long? = null
	var f1: Long? = null
	var f2: Long? = null
	var f3: Long? = null
	var f4: Long? = null
	var f5: Long? = null
	var f6: Long? = null
	var f7: Long? = null
	var f8: Long? = null
	var f9: Long? = null

	/**
	 * Reads the value from the specified general-purpose register.
	 *
	 * @param registers The [FunctionRegisterType] to read from.
	 * @return The value stored in the register as a [Long].
	 * @throws NullRegisterException If the register has not been initialised (has a null value).
	 */
	fun read(registers: FunctionRegisterType): Long {
		try {
			return readUnsafe(registers)!!
		} catch (e: NullPointerException) {
			errors.NullRegisterException(registers.toString().toSuperRegisterType())
			exitProcess(11) // To satisfy the compiler. This shouldn't trigger
		}


	}


	fun readUnsafe(registers: FunctionRegisterType): Long? {
		return when (registers) {
			FunctionRegisterType.F0 -> f0
			FunctionRegisterType.F1 -> f1
			FunctionRegisterType.F2 -> f2
			FunctionRegisterType.F3 -> f3
			FunctionRegisterType.F4 -> f4
			FunctionRegisterType.F5 -> f5
			FunctionRegisterType.F6 -> f6
			FunctionRegisterType.F7 -> f7
			FunctionRegisterType.F8 -> f8
			FunctionRegisterType.F9 -> f9
		}
	}

	/**
	 * Writes a value to the specified general-purpose register.
	 *
	 * @param registers The [FunctionRegisterType] to write to.
	 * @param value The [Long] value to write to the register.
	 */
	fun write(registers: FunctionRegisterType, value: Long?) {
		when (registers) {
			FunctionRegisterType.F0 -> f0 = value
			FunctionRegisterType.F1 -> f1 = value
			FunctionRegisterType.F2 -> f2 = value
			FunctionRegisterType.F3 -> f3 = value
			FunctionRegisterType.F4 -> f4 = value
			FunctionRegisterType.F5 -> f5 = value
			FunctionRegisterType.F6 -> f6 = value
			FunctionRegisterType.F7 -> f7 = value
			FunctionRegisterType.F8 -> f8 = value
			FunctionRegisterType.F9 -> f9 = value
		}
	}
}