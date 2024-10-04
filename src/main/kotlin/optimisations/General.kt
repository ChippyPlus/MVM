package optimisations


import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.InstructData


typealias RegisterTree = MutableMap<SuperRegisterType, MutableList<ContextUnderstanding>>

enum class StatusType {
    USE, DECLARED, FUNCTION_R4, FUNCTION_R3

}

val r4Outs = setOf("add", "sub", "mul", "div", "mod", "strlen", "strcmp", "strcat")
val r3Outs = setOf("and", "or", "not", "xor", "shr", "shl")


val test = listOf(
    InstructData(name = "lit", arrayOf(SuperRegisterType.G2, 10)),
    InstructData(name = "lit", arrayOf(SuperRegisterType.G2, 20)),
    InstructData(name = "lit", arrayOf(SuperRegisterType.G1, 30)),
    InstructData(name = "add", arrayOf(SuperRegisterType.G1, SuperRegisterType.G2)),
    InstructData(name = "lit", arrayOf(SuperRegisterType.G2, 30)),
    InstructData(name = "lit", arrayOf(SuperRegisterType.G2, 40)),
    InstructData(name = "push", values = arrayOf(SuperRegisterType.G2)),
    InstructData(name = "prints", values = arrayOf())
)

data class ContextUnderstanding(val name: SuperRegisterType, var status: StatusType, val lineNumber: Int)


data class FunctionUnderstanding(val name: String, val lineNumber: Int)


//class General(/* val globalInfo: List<InstructData> */) {
fun parseRegisterStatuses(): MutableList<ContextUnderstanding> {
    val tracked = mutableListOf<ContextUnderstanding>()

    for ((index, i) in test.withIndex()) {
        if (i.name == "lit") {
            tracked.add(
                ContextUnderstanding(
                    name = i.values[0] as SuperRegisterType,
                    status = StatusType.DECLARED,
                    lineNumber = index
                )
            )
        } else if (i.name in r4Outs) {
            tracked.add(
                ContextUnderstanding(
                    name = SuperRegisterType.R4,
                    status = StatusType.DECLARED,
                    lineNumber = index,
                )
            )
        } else if (i.name in r3Outs) {
            tracked.add(
                ContextUnderstanding(
                    name = SuperRegisterType.R3,
                    status = StatusType.DECLARED,
                    lineNumber = index,
                )
            )
        } else {
            for (j in i.values.withIndex()) {
                if (j.value is SuperRegisterType) {
                    tracked.add(
                        ContextUnderstanding(
                            name = j.value as SuperRegisterType,
                            status = StatusType.USE,
                            lineNumber = index
                        )
                    )
                }
            }
        }
    }
    return tracked
}

fun reverseFindRedundancy(inp: MutableList<ContextUnderstanding>): MutableSet<Int> {
    val uses = mutableSetOf<SuperRegisterType>()
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

fun removeRedundancy(inp: MutableSet<Int>, instructs: List<InstructData>): List<InstructData> {
    val n = instructs.toMutableList()
    for (i in inp) {
        n.removeAt(i)
    }
    return n
}

//}


fun main() {
    var out = removeRedundancy(
        inp = reverseFindRedundancy(parseRegisterStatuses()), instructs = test
    )
    while (true) {
        val tmpOut = removeRedundancy(
            inp = reverseFindRedundancy(parseRegisterStatuses()), instructs = test
        )
        if (tmpOut != out) {
            out = tmpOut
        } else {
            break
        }
    }
    out.forEach(::println)
}