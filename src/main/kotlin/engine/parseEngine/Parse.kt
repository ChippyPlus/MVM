package engine.parseEngine

import data.registers.RegisterType
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
	val tmp = insertValuesLit()


	fun insertValuesLit(): List<TokenData> {
		val newT = mutableListOf<TokenData>()
		val valuesOfRegisters = mutableMapOf<RegisterType, Long>()
		instructedTokens.forEach {
			if (it.name == "lit") valuesOfRegisters[it.values[0] as RegisterType] = it.values[1] as Long
		}

		for (i in instructedTokens) {
			if (i.type == InstructType.RegisterLong) newT.add(i)
			else if (i.type == InstructType.RegisterRegister && i.values[0] as RegisterType in valuesOfRegisters.keys && i.values[1] as RegisterType in valuesOfRegisters.keys) {
				newT.add(
					TokenData(
						i.name, i.type, arrayOf(
							valuesOfRegisters[i.values[0] as RegisterType]!!,
							valuesOfRegisters[i.values[1] as RegisterType] as Any
						)
					)
				)
			}
		}

		return newT.toList()
	}
}


fun main() {
	val p = Parse(File("main.kar"))
	p.tmp.forEach(::println)
}