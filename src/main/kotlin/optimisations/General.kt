package optimisations

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.InstructData


typealias RegisterTree = MutableMap<SuperRegisterType, MutableList<RegisterUnderstanding>>

enum class StatusType {
    USE, DECLARED,

}


data class RegisterUnderstanding(val register: SuperRegisterType, var status: StatusType, val lineNumber: Int)

class General(/* val globalInfo: List<InstructData> */) {
    fun parseRegisterStatuses(): MutableList<RegisterUnderstanding> {
        val tracked = mutableListOf<RegisterUnderstanding>()
        val test = listOf(
            InstructData(name = "lit", arrayOf(SuperRegisterType.G2, 10)),
            InstructData(name = "lit", arrayOf(SuperRegisterType.G2, 20)),
            InstructData(name = "lit", arrayOf(SuperRegisterType.G1, 30)),
            InstructData(name = "add", arrayOf(SuperRegisterType.G1, SuperRegisterType.G2)),
            InstructData(name = "lit", arrayOf(SuperRegisterType.G2, 40)),
            InstructData(name = "push", values = arrayOf(SuperRegisterType.G2)),
            InstructData(name = "prints", values = arrayOf())
        )

        for ((index, i) in test.withIndex()) {
            if (i.name == "lit") {
                tracked.add(RegisterUnderstanding(i.values[0] as SuperRegisterType, StatusType.DECLARED, index))
            } else {
                for (j in i.values.withIndex()) {
                    if (j.value is SuperRegisterType) {
                        tracked.add(RegisterUnderstanding(j.value as SuperRegisterType, StatusType.USE, index))
                    }
                }
            }
        }
        tracked.forEach(::println)
        return tracked
    }

    fun findRedundancy(tracked: MutableList<RegisterUnderstanding>): RegisterTree {
        val singleRegisterUses: RegisterTree =
            emptyMap<SuperRegisterType, MutableList<RegisterUnderstanding>>().toMutableMap()


        for (i in tracked) {
            if (i.register !in singleRegisterUses.keys) {
                singleRegisterUses[i.register] = mutableListOf()
            }

            singleRegisterUses[i.register]!!.add(i)

        }
//        for (i in singleRegisterUses) {
//            println(i)
//        }
        return singleRegisterUses
    }

    fun removeRedundancy(singleRegisterUses: RegisterTree) {

    }

}

fun main() {
    General().findRedundancy(General().parseRegisterStatuses())

}