package internals.instructions.bitwise

import data.registers.IntelRegisters
import data.registers.RegisterType.R3
import data.registers.intelNames
import errors
import helpers.toLong
import registers


/**
 * Represents the bitwise operations unit within the virtual machine.
 *
 * This class provides functions for performing bitwise operations on register values.
 */
open class Bitwise {

	fun call(name: String, function: () -> Long) {
		try {
			val out = function()

			registers.write(
				R3, value = out
			)
			registers.write(intelNames[IntelRegisters.ENSF], true.toLong())


		} catch (e: Exception) {
			errors.run { this@run.GeneralBitwiseException(message = name) }
		}
	}

}