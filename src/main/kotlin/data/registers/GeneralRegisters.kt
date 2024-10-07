package data.registers

import data.registers.enumIdenifiers.GeneralRegisterType
import errors
import helpers.toSuperRegisterType
import kotlin.system.exitProcess

/**
 * Represents the set of general-purpose registers in the virtual machine.
 *
 * General-purpose registers are used for a wide range of computations and data manipulation.
 */
class GeneralRegisters {
	var g1: Long? = null
	var g2: Long? = null
	var g3: Long? = null
	var g4: Long? = null

	/**
	 * Reads the value from the specified general-purpose register.
	 *
	 * @param registers The [GeneralRegisterType] to read from.
	 * @return The value stored in the register as a [Long].
	 * @throws NullRegisterException If the register has not been initialised (has a null value).
	 */
	fun read(registers: GeneralRegisterType): Long {
		try {
			return when (registers) {
				GeneralRegisterType.G1 -> g1!!
				GeneralRegisterType.G2 -> g2!!
				GeneralRegisterType.G3 -> g3!!
				GeneralRegisterType.G4 -> g4!!
			}
		} catch (e: NullPointerException) {
			errors.NullRegisterException(registers.toString().toSuperRegisterType())
			exitProcess(11) // To satisfy the compiler. This shouldn't trigger
		}
	}

	fun readUnsafe(registers: GeneralRegisterType): Long? {
		return when (registers) {
			GeneralRegisterType.G1 -> g1
			GeneralRegisterType.G2 -> g2
			GeneralRegisterType.G3 -> g3
			GeneralRegisterType.G4 -> g4
		}
	}


	/**
	 * Writes a value to the specified general-purpose register.
	 *
	 * @param registers The [GeneralRegisterType] to write to.
	 * @param value The [Long] value to write to the register.
	 */
	fun write(registers: GeneralRegisterType, value: Long) {
		when (registers) {
			GeneralRegisterType.G1 -> g1 = value
			GeneralRegisterType.G2 -> g2 = value
			GeneralRegisterType.G3 -> g3 = value
			GeneralRegisterType.G4 -> g4 = value
		}
	}
}