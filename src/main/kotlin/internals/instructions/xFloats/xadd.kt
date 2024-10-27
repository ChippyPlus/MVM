package internals.instructions.xFloats

import data.registers.RegisterType
import errors
import registers
import kotlin.system.exitProcess

fun XFloats.xAdd(operand1: RegisterType, operand2: RegisterType) {
	val o1 = registers.readX(operand1)
	val o2 = registers.readX(operand2)

	val out = if (o1 is Double && o2 is Double) {
		o1 + o2
	} else if (o1 is Float && o2 is Float) {
		o1 + o2
	} else {
		errors.InvalidRegisterTypeException("$operand1 and $operand2 are not the same type")
		exitProcess(1)
	}


	registers.writeX(RegisterType.R5, out)
}