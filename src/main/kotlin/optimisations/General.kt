package optimisations

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.InstructData


enum class StatusType {
    WRITTEN,
    READ,
}


data class RegisterUnderstanding(val register: SuperRegisterType, var status: StatusType)

class General(/* val globalInfo: List<InstructData> */) {
    fun findUsagesRegisters() {
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

        for (i in test) {
            if (i.name == "lit") {
                tracked.add(RegisterUnderstanding(i.values[0] as SuperRegisterType, StatusType.WRITTEN))
            } else {
                for (j in i.values.withIndex()) {
                    if (j.value is SuperRegisterType) {
                        tracked.add(RegisterUnderstanding(j.value as SuperRegisterType, StatusType.READ))

                    }
                }
            }
        }
        tracked.forEach(::println)
    }
}

fun main() {
    General().findUsagesRegisters()
}