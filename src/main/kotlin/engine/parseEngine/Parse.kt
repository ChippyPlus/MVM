package engine.parseEngine

import data.registers.RegisterType
import java.io.File


@Suppress("ArrayInDataClass")
data class TokenData(
	val name: String,
	val type: InstructType,
	val values: Array<Any>,
	val result: String? = calcResult(name, values),
)

private fun calcResult(name: String, values: Array<Any>): String? {
	return when (name) {
		"add", "sub", "mul", "div", "mod", "pow" -> "R4"
		"xadd", "xsub", "xmul", "xdiv", "xmod", "xpow" -> "R5"
		"shl", "shr", "and", "or", "xor", "not" -> "R3"
		"gt", "lt" -> "I3"
		"EQ" -> "I4"
		"load" -> (values[1] as RegisterType).toString()
		"store" -> (values[0] as RegisterType).toString()
		"pop" -> (values[0] as RegisterType).toString()
		"peek" -> (values[0] as RegisterType).toString()
		"str" -> (values[0] as RegisterType).toString()
		"inr" -> "R6"
		"printr" -> "IO"
		"lit" -> (values[0] as RegisterType).toString()
		else -> null
	}
}


enum class InstructType {
	Ignore, Register, RegisterRegister, RegisterLong, Long, LongRegister, SingleString, StrSpecific, None, Settype, Xlit, SystemCall
}
typealias TokenList = List<List<String>>

class Parse(f: File) {
	val file = f.readLines()
	val tokens: TokenList = tokenV1Generation(file)
	val instructedTokens = useInstruct(tokens)

	fun tokenise(): List<TokenData> {
		return instructedTokens
	}

}


