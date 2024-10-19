package internals.instructions.arithmetic

import data.registers.IntelRegisters
import data.registers.intelNames
import helpers.toLong
import registers
import kotlin.math.sign


/**
 * Represents the arithmetic operations unit within the virtual machine.
 *
 * This class provides functions for performing arithmetic operations on register values.
 */
open class Arithmetic {
	fun zeroFlag(out: Long) {
		if (out == 0L) registers.write(
			intelNames[IntelRegisters.ZF], true.toLong()
		)
		else registers.write(
			intelNames[IntelRegisters.ZF], false.toLong()
		)
	}

	fun signFlag(out: Long) {
		if (out.sign == -1) {
			registers.write(
				intelNames[IntelRegisters.SF], true.toLong()
			)
		} else {
			registers.write(
				intelNames[IntelRegisters.SF], false.toLong()
			)
		}
	}
}