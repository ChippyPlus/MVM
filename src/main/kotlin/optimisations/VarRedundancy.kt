package optimisations

import data.registers.enumIdenifiers.SuperRegisterType
import engine.execution.InstructData
import r3Outs
import r4Outs


enum class StatusType {
    USE, DECLARED
}




data class ContextUnderstanding(val name: SuperRegisterType, var status: StatusType, val lineNumber: Int)


class VarRedundancy(val globalInfo: List<InstructData>) {
    private fun parseRegisterStatuses(): MutableList<ContextUnderstanding> {
        val tracked = mutableListOf<ContextUnderstanding>()

        for ((index, i) in globalInfo.withIndex()) {
            when (i.name) {
                "lit" -> {
                    tracked.add(
                        ContextUnderstanding(
                            name = i.values[0] as SuperRegisterType, status = StatusType.DECLARED, lineNumber = index
                        )
                    )
                }

                in r4Outs -> {
                    tracked.add(
                        ContextUnderstanding(
                            name = SuperRegisterType.R4,
                            status = StatusType.DECLARED,
                            lineNumber = index,
                        )
                    )
                }

                in r3Outs -> {
                    tracked.add(
                        ContextUnderstanding(
                            name = SuperRegisterType.R3,
                            status = StatusType.DECLARED,
                            lineNumber = index,
                        )
                    )
                }

                else -> {
                    for (j in i.values.withIndex()) {
                        if (j.value is SuperRegisterType) {
                            tracked.add(
                                ContextUnderstanding(
                                    name = j.value as SuperRegisterType, status = StatusType.USE, lineNumber = index
                                )
                            )
                        }
                    }
                }
            }
        }
        return tracked
    }

    private fun reverseFindRedundancy(inp: MutableList<ContextUnderstanding>): MutableSet<Int> {
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

    private fun removeRedundancy(inp: MutableSet<Int>): List<InstructData> {
        val n = globalInfo.toMutableList()
        for (i in inp) {
            n.removeAt(i)
        }
        return n
    }

    fun removeRedundancy(): List<InstructData> {
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
}


