package engine.parseEngine

import java.io.File


@Suppress("ArrayInDataClass")
data class TokenData(val name: String, val type: InstructType, val values: Array<Any>)

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


