package engine.parseEngine

import data.registers.RegisterType
import data.registers.toRegisterDataType
import errors
import helpers.toDoubleOrFloatBasedOnDataType
import helpers.toRegisterType

class InstructBuild(val line: List<String>) {

	fun xlit() = TokenData(
		name = "xlit",
		type = InstructType.Xlit,
		values = arrayOf(line[1].toRegisterType(), line[2].toDoubleOrFloatBasedOnDataType(line[1].toRegisterType()))
	)

	fun none() = TokenData(name = line[0], type = InstructType.None, values = emptyArray())

	fun settype() = TokenData(
		name = "settype",
		type = InstructType.Settype,
		values = arrayOf(line[1].toRegisterType(), line[2].toRegisterDataType())
	)

	fun register() = TokenData(
		name = line[0], type = InstructType.Register, values = arrayOf(line[1].toRegisterType())
	)

	fun registerRegister() = TokenData(
		name = line[0],
		type = InstructType.RegisterRegister,
		values = arrayOf(line[1].toRegisterType(), line[2].toRegisterType())
	)

	fun registerLong() = TokenData(
		name = line[0], type = InstructType.RegisterLong, values = arrayOf(line[1].toRegisterType(), line[2].toLong())
	)

	fun longRegister() = TokenData(
		name = line[0], type = InstructType.LongRegister, values = arrayOf(line[1].toLong(), line[2].toRegisterType())
	)

	fun singleString() = TokenData(
		name = line[0], type = InstructType.SingleString, values = arrayOf(line[1])
	)

	fun strSpecific() = TokenData(
		name = line[0], type = InstructType.StrSpecific, values = arrayOf(
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
		type = InstructType.SystemCall,
		values = arrayOf(RegisterType.S1, RegisterType.S2, RegisterType.S3, RegisterType.S4)
	)
}