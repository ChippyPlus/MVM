package data.registers

import data.registers.enumIdenifiers.ReturnRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess

/**
 * Represents the set of return registers in the virtual machine.
 *
 * Return registers are primarily used to store results from operations & system calls
 */
class ReturnRegisters {
	var r0: Long? = null
	var r1: Long? = null
	var r2: Long? = null
	var r3: Long? = null
	var r4: Long? = null
	var r5: Long? = null
	var r6: Long? = null
	var r7: Long? = null
	var r8: Long? = null
	var r9: Long? = null

	/**
	 * Reads the value from the specified return register.
	 *
	 * @param registers The [ReturnRegisterType] to read from.
	 * @return The value stored in the register as a [Long].
	 * @throws NullRegisterException If the register has not been initialised (has a null value).
	 */
	fun read(registers: ReturnRegisterType): Long {
		try {
			return readUnsafe(registers)!!
		} catch (e: NullPointerException) {
			errors.NullRegisterException(registers.toString().toSuperRegisterType())
			exitProcess(11) // To satisfy the compiler. This shouldn't trigger
		}
	}


	fun readUnsafe(registers: ReturnRegisterType): Long? {
		return when (registers) {
			ReturnRegisterType.R0 -> r0
			ReturnRegisterType.R1 -> r1
			ReturnRegisterType.R2 -> r2
			ReturnRegisterType.R3 -> r3
			ReturnRegisterType.R4 -> r4
			ReturnRegisterType.R5 -> r5
			ReturnRegisterType.R6 -> r6
			ReturnRegisterType.R7 -> r7
			ReturnRegisterType.R8 -> r8
			ReturnRegisterType.R9 -> r9
		}
	}

	/**
	 * Writes a value to the specified return register.
	 *
	 * @param registers The [ReturnRegisterType] to write to.
	 * @param value The [Long] value to write to the register.
	 */
	fun write(registers: ReturnRegisterType, value: Long?) {
		when (registers) {
			ReturnRegisterType.R0 -> r0 = value
			ReturnRegisterType.R1 -> r1 = value
			ReturnRegisterType.R2 -> r2 = value
			ReturnRegisterType.R3 -> r3 = value
			ReturnRegisterType.R4 -> r4 = value
			ReturnRegisterType.R5 -> r5 = value
			ReturnRegisterType.R6 -> r6 = value
			ReturnRegisterType.R7 -> r7 = value
			ReturnRegisterType.R8 -> r8 = value
			ReturnRegisterType.R9 -> r9 = value
		}
	}
}