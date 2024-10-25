package internals.instructions.arithmetic

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.toDouble
import registers
import kotlin.math.sign


/**
 * Represents the arithmetic operations unit within the virtual machine.
 *
 * This class provides functions for performing arithmetic operations on register values.
 */
open class Arithmetic {
	fun zeroFlag(out: Double) {
		if (out.toLong() == 0L) registers.write(
			intelNames[IntelRegisters.ZF], true.toDouble()
		)
		else registers.write(
			intelNames[IntelRegisters.ZF], false.toDouble()
		)
	}

	fun zeroFlag(out: Long) {
		zeroFlag(out.toDouble())
	}

	fun signFlag(out: Long) {
		signFlag(out.toDouble())
	}


	fun signFlag(out: Double) {
		if (out.sign.toInt() == -1) {
			registers.write(
				intelNames[IntelRegisters.SF], true.toDouble()
			)
		} else {
			registers.write(
				intelNames[IntelRegisters.SF], false.toDouble()
			)
		}
	}


	fun call(name: String, function: () -> Unit?) {

		val out = try {
			function()

		} catch (e: Exception) {
			errors.GeneralArithmeticException(message = name)
		}
		signFlag(registers.read(RegisterType.R4))
		zeroFlag(registers.read(RegisterType.R4))



		if (out != null) {
			registers.write(intelNames[IntelRegisters.ENSF], true.toDouble())
		} else {
			registers.write(intelNames[IntelRegisters.ENSF], false.toDouble())
		}
	}
}