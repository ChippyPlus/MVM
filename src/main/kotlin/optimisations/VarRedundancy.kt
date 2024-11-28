package optimisations

import data.registers.RegisterType
import engine.execution.InstructData
import r3Outs
import r4Outs


enum class StatusType {
	USE, DECLARED
}


data class ContextUnderstanding(val name: RegisterType, var status: StatusType, val lineNumber: Int)

@Deprecated("Does not work!")
class VarRedundancy(val globalInfo: List<InstructData>) {
	@Deprecated("Does not work!")
	fun parseRegisterStatuses(): MutableList<ContextUnderstanding> {
		val tracked = mutableListOf<ContextUnderstanding>()

		for ((index, i) in globalInfo.withIndex()) {
			when (i.name) {
				"lit" -> {
					tracked.add(
						ContextUnderstanding(
							name = i.values[0] as RegisterType, status = StatusType.DECLARED, lineNumber = index
						)
					)
				}

				in r4Outs -> {
					tracked.add(
						ContextUnderstanding(
							name = RegisterType.R4,
							status = StatusType.DECLARED,
							lineNumber = index,
						)
					)
				}

				in r3Outs -> {
					tracked.add(
						ContextUnderstanding(
							name = RegisterType.R3,
							status = StatusType.DECLARED,
							lineNumber = index,
						)
					)
				}

				else -> {
					for (j in i.values.withIndex()) {
						if (j.value is RegisterType) {
							tracked.add(
								ContextUnderstanding(
									name = j.value as RegisterType, status = StatusType.USE, lineNumber = index
								)
							)
						}
					}
				}
			}
		}
		return tracked
	}

	@Deprecated("Does not work!")
	fun removeRedundancy(inp: MutableSet<Int>): List<InstructData> {
		val n = globalInfo.toMutableList()
		for (i in inp) {
			n.removeAt(i)
		}
		return n
	}

	@Deprecated("Does not work!")
	fun cleanRedundancy(): List<InstructData> {
		var out = removeRedundancy(
			inp = reverseFindRedundancy(parseRegisterStatuses())
		)
		while (true) {
			val tmpOut = removeRedundancy(
				inp = reverseFindRedundancy(parseRegisterStatuses())
			)
			if (tmpOut != out) {

				out = tmpOut
			} else {
				break
			}
		}
		return out
	}

	@Deprecated("Does not work!")
	fun reverseFindRedundancy(inp: MutableList<ContextUnderstanding>): MutableSet<Int> {
		val uses = mutableSetOf<RegisterType>()
		val u2 = mutableSetOf<Int>()
		inp.reverse()
		for (i in inp) {
			if (i.status == StatusType.USE) {
				uses.add(i.name)
			} else if (i.status == StatusType.DECLARED && uses.contains(i.name)) {
				uses.remove(i.name)
			} else {
				u2.add(i.lineNumber)
			}
		}

		return u2
	}
}

//fun main() {
//    val x = VarRedundancy(globalInfo = parser(File("main.kar")))
//    val y = x.parseRegisterStatuses()
//    y.forEach(::println)
//    val z = x.cleanRedundancy()
//    println(z)
//}


