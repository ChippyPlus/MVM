package optimisations

import data.registers.RegisterType
import engine.parseEngine.InstructType
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
	val inp = o.insertValuesLit().toMutableList()


	fun updateNextR4(startIndex: Int) {
		for ((index, content) in inp.toMutableList().subList(startIndex, inp.size).withIndex()) {
			if (content.name in arrayOf("add", "sub", "mul", "div", "mod", "pow") || (content.name in arrayOf(
					"lit", "mov", "cpy", "load", "printr"
				) && RegisterType.R4 in content.values)
			) {
				println("hit")
				for (j in content.values.withIndex()) {
					if (j.value is RegisterType && j.value == RegisterType.R4) {
						content.values[j.index] = RegisterType.R3
						inp[index] = content
					}
				}
			}
		}
	}



	for (i in inp.toMutableList().withIndex()) {
		if (i.value.name == "mul") {
			if (checkBase2(i.value.values[0] as Long) != null) {
				inp.removeAt(i.index)
				inp.add(
					i.index,
					TokenData("shl", InstructType.RegisterRegister, arrayOf(i.value.values[1], i.value.values[0]))
				)
				updateNextR4(i.index)
			} else if (checkBase2(i.value.values[1] as Long) != null) {
				inp.removeAt(i.index)
				inp.add(
					i.index,
					TokenData("shl", InstructType.RegisterRegister, arrayOf(i.value.values[0], i.value.values[1]))
				)
				updateNextR4(i.index)
			}
		}
	}

	inp.forEach(::println)
}


