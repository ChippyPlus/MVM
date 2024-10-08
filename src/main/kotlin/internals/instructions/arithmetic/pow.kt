package internals.instructions.arithmetic

import data.registers.enumIdenifiers.ReturnRegisterType.R4
import data.registers.enumIdenifiers.SuperRegisterType
import errors
import helpers.fullRegisterRead
import returnRegisters
import kotlin.math.pow


fun Arithmetic.pow(operand1: SuperRegisterType, operand2: SuperRegisterType): Unit = try {
	val a: Long = fullRegisterRead(register = operand1)
	val b: Long = fullRegisterRead(register = operand2)
	returnRegisters.run {
		write(
			registers = R4, value = b.toDouble().pow(a.toDouble()).toLong()
		)
	}
} catch (e: Exception) {
	errors.run { this@run.GeneralArithmeticException(message = "pow") }
}