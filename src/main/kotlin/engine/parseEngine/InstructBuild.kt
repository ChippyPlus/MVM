package engine.parseEngine

import data.registers.RegisterType
import data.registers.toRegisterDataType
import engine.parseEngine.InstructType.*
import errors
import helpers.toDoubleOrFloatBasedOnDataType
import helpers.toRegisterType


class InstructBuild(val line: List<String>) {

	fun ignore() = TokenData(
		name = "ignore", values = arrayOf(), type = Ignore
	)

	fun xlit() = TokenData(
		name = "xlit",
		type = Xlit,
		values = arrayOf(line[1].toRegisterType(), line[2].toDoubleOrFloatBasedOnDataType(line[1].toRegisterType()))
	)

	fun none() = TokenData(name = line[0], type = None, values = emptyArray())

	fun settype() = TokenData(
		name = "settype", type = Settype, values = arrayOf(line[1].toRegisterType(), line[2].toRegisterDataType())
	)

	fun register() = TokenData(
		name = line[0], type = Register, values = arrayOf(line[1].toRegisterType())
	)

	fun registerRegister() = TokenData(
		name = line[0], type = RegisterRegister, values = arrayOf(line[1].toRegisterType(), line[2].toRegisterType())
	)

	fun registerLong() = TokenData(
		name = line[0], type = RegisterLong, values = arrayOf(line[1].toRegisterType(), line[2].toLong())
	)

	fun longRegister() = TokenData(
		name = line[0], type = LongRegister, values = arrayOf(line[1].toLong(), line[2].toRegisterType())
	)

	fun singleString() = TokenData(
		name = line[0], type = SingleString, values = arrayOf(line[1])
	)

	fun strSpecific() = TokenData(
		name = line[0], type = StrSpecific, values = arrayOf(
			line[1].toRegisterType(), try {
				line.joinToString(" ").split("\"")[1]
			} catch (e: IndexOutOfBoundsException) {
				errors.InvalidArgumentFormatException(
					"Any", shouldBe = "String"
				)
			}

		)
	)

	fun long() = TokenData(
		name = line[0], type = InstructType.Long, values = arrayOf(line[1].toLong())
	)

	fun systemCall() = TokenData(
		name = "syscall",
		type = SystemCall,
		values = arrayOf(RegisterType.S1, RegisterType.S2, RegisterType.S3, RegisterType.S4)
	)


	private fun calcResult(): String? {
		return when (line[0]) {
			"add", "sub", "mul", "div", "mod", "pow" -> "R4"
			"xadd", "xsub", "xmul", "xdiv", "xmod", "xpow" -> "R5"
			"shl", "shr", "and", "or", "xor", "not" -> "R3"
			"gt", "lt" -> "I3"
			"EQ" -> "I4"
			"load" -> line[2].toRegisterType().toString()
			"store" -> line[1].toRegisterType().toString()
			"pop" -> line[1].toRegisterType().toString()
			"peek" -> line[1].toRegisterType().toString()
			"str" -> line[1].toRegisterType().toString()
			"inr" -> "R6"
			else -> null
		}
	}
}