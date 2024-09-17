package internals.instructions

import data.memory.MemoryAddress
import data.registers.enumIdenifiers.SuperRegisterType

@Suppress("unused")
class Instruction {

	data class StrCpy(val source: SuperRegisterType, val destination: SuperRegisterType)
	data class StrCmp(val string1: SuperRegisterType, val string2: SuperRegisterType)
	data class StrCat(
		val string1: SuperRegisterType,
		val string2: SuperRegisterType,
	)

	data class SubStr(
		val string: SuperRegisterType,
		val start: SuperRegisterType,
		val length: SuperRegisterType,
	)

	data class Find(val string: SuperRegisterType, val substring: SuperRegisterType)
	data class Cpy(val register1: SuperRegisterType, val register2: SuperRegisterType)
	data class Strlen(val addressRegister: SuperRegisterType)
	data class Printr(val register: SuperRegisterType)
	data class Str(val targetAddress: SuperRegisterType, val string: String)
	data class Mod(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Eq(val operand1: SuperRegisterType, val operand2: SuperRegisterType)

	@Deprecated("Eh it never worked and useless")
	data class Halt(val nothing: Nothing? = null)
	data class Mov(val source: SuperRegisterType, val destination: SuperRegisterType)
	data class Add(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Sub(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Mul(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Div(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Jmp(val targetAddress: Int)
	data class Jz(val targetAddress: Int, val testRegister: SuperRegisterType)
	data class Jnz(val targetAddress: Int, val testRegister: SuperRegisterType)
	data class Peek(val destination: SuperRegisterType)
	data class Push(val source: SuperRegisterType)
	data class Pop(val destination: SuperRegisterType)
	data class Syscall(
		val systemCallNumber: SuperRegisterType,
		val argument1: SuperRegisterType,
		val argument2: SuperRegisterType,
		val argument3: SuperRegisterType,
	)

	data class Load(val memoryAddress: MemoryAddress, val destination: SuperRegisterType)
	data class Store(val source: SuperRegisterType, val memoryAddress: SuperRegisterType)
	data class And(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Or(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Xor(val operand1: SuperRegisterType, val operand2: SuperRegisterType)
	data class Not(val operand: SuperRegisterType)
	data class Shl(val operand: SuperRegisterType, val shiftAmount: SuperRegisterType)
	data class Shr(val operand: SuperRegisterType, val shiftAmount: SuperRegisterType)
	data class Lit(val destination: SuperRegisterType, val value: Long)
	data class Prints(val nothing: Nothing? = null)


}