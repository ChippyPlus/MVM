package helpers


import data.registers.enumIdenifiers.*
import errors
import kotlin.system.exitProcess

/**
 * Reads the value from the specified register, regardless of its type.
 *
 * This function provides a unified way to access the value of any register ([SuperRegisterType])
 * by internally converting it to the appropriate specific register type.
 *
 * @param register The register ([SuperRegisterType]) to read from.
 * @return The value stored in the register as a [Long].
 * @throws NullRegisterException If the register has not been initialised (has a null value).
 */

fun registerRead(register: SuperRegisterType): Long {
	return try {
		registerReadUnsafe(register)!!
	} catch (e: NullPointerException) {
		errors.NullRegisterException(register)
		exitProcess(11)
	}
}