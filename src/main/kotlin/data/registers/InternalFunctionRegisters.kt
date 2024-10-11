package data.registers

import data.registers.enumIdenifiers.FunctionRegisterType
import data.registers.enumIdenifiers.InternalFunctionRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess

/**
 * Represents the set of function registers in the virtual machine.
 *
 * Internal Function Registers are used are only meant to be used by internal functions in the stdlib
 */
class InternalFunctionRegisters {
	var if0: Long? = null
	var if1: Long? = null
	var if2: Long? = null
	var if3: Long? = null
	var if4: Long? = null
	var if5: Long? = null
	var if6: Long? = null
	var if7: Long? = null
	var if8: Long? = null
	var if9: Long? = null

	/**
	 * Reads the value from the specified general-purpose register.
	 *
	 * @param registers The [FunctionRegisterType] to read from.
	 * @return The value stored in the register as a [Long].
	 * @throws NullRegisterException If the register has not been initialised (has a null value).
	 */
	fun read(registers: InternalFunctionRegisterType): Long {
		try {
			return readUnsafe(registers)!!
		} catch (e: NullPointerException) {
			errors.NullRegisterException(registers.toString().toSuperRegisterType())
			exitProcess(0)
		}
	}


	fun readUnsafe(registers: InternalFunctionRegisterType): Long? {
		return when (registers) {
			InternalFunctionRegisterType.IF0 -> if0
			InternalFunctionRegisterType.IF1 -> if1
			InternalFunctionRegisterType.IF2 -> if2
			InternalFunctionRegisterType.IF3 -> if3
			InternalFunctionRegisterType.IF4 -> if4
			InternalFunctionRegisterType.IF5 -> if5
			InternalFunctionRegisterType.IF6 -> if6
			InternalFunctionRegisterType.IF7 -> if7
			InternalFunctionRegisterType.IF8 -> if8
			InternalFunctionRegisterType.IF9 -> if9
		}
	}

	/**
	 * Writes a value to the specified general-purpoe register.
	 *
	 * @param registers The [InternalFunctionRegisterType] to write to.
	 * @param value The [Long] value to write to the register.
	 */
	fun write(registers: InternalFunctionRegisterType, value: Long?) {
		when (registers) {
			InternalFunctionRegisterType.IF0 -> if0 = value
			InternalFunctionRegisterType.IF1 -> if1 = value
			InternalFunctionRegisterType.IF2 -> if2 = value
			InternalFunctionRegisterType.IF3 -> if3 = value
			InternalFunctionRegisterType.IF4 -> if4 = value
			InternalFunctionRegisterType.IF5 -> if5 = value
			InternalFunctionRegisterType.IF6 -> if6 = value
			InternalFunctionRegisterType.IF7 -> if7 = value
			InternalFunctionRegisterType.IF8 -> if8 = value
			InternalFunctionRegisterType.IF9 -> if9 = value
		}
	}
}