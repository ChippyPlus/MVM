package internals.instructions.arithmetic

import data.registers.IntelRegisters
import data.registers.RegisterType
import data.registers.intelNames
import errors
import helpers.toLong
import registers


fun Arithmetic.lt(operand1: RegisterType, operand2: RegisterType) = try {

	val out = registers.read(register = operand1) > registers.read(register = operand2)

	registers.write(
		intelNames[IntelRegisters.GF], if (!out) true.toLong() else false.toLong()
	)
} catch (e: Exception) {
	errors.run { this@run.GeneralArithmeticException(message = "lt") }
}

