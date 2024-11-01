package optimisations

import data.registers.RegisterType
import engine.parseEngine.Parse
import engine.parseEngine.TokenData
import java.io.File
import kotlin.math.pow

fun checkBase2(n: Long): Int? {
	var index = 0
	var no: Long
	while (true) {
		index++
		no = 2.0.pow(index).toLong()
		if (no == n) return index
		else if (no > n) return null
	}
}


val f = Parse(File("main.mar")).tokenise()


fun main() {
	val o = Optimisations(f)
	val inp = removeRedundantDeclarations(o.globalInfo)

	o.globalInfo.forEachIndexed { index, content -> println("[$index] $content") }
	println("----------------")
	inp.forEach(::println)
}


fun removeRedundantDeclarations(tokens: List<TokenData>): List<TokenData> {
	val totalWrites = mutableSetOf<Int>()

	val reads = mutableMapOf<RegisterType, Int>()
	val writes = mutableMapOf<RegisterType, Int>()


	for ((index, content) in tokens.withIndex()) {
		for (i in content.values) {
			if (i is RegisterType && (content.name !in arrayOf("lit", "mov", "cpy", "load"))) {
				reads[i] = index
			}
		}
	}

	for ((registerRead, index) in reads) {
		val possibleRangOfDeclaration = tokens.subList(0, index).reversed()
		for ((indexIndent, i) in possibleRangOfDeclaration.withIndex()) {
			if (i.name == "lit" && i.values[0] == registerRead) {
				writes[registerRead] = tokens.subList(0, index).size - (indexIndent + 1)
				break
			}
		}
	}

	for (i in tokens.withIndex()) if (i.value.name == "lit") totalWrites.add(i.index)

	val newT = mutableMapOf<Int, TokenData>()

	for (i in tokens.withIndex()) {
		newT[i.index] = i.value
	}

	totalWrites.forEach { if (it !in writes.values) newT.remove(it) }

	val final = newT.map { it.value }


	return final
}