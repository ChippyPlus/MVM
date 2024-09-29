package engine.v2

import engine.execution.InstructData

class Compile {
    fun execute(input: List<InstructData>): String {
        var uneditedFile = ""
        val transMapIDs = TransMapIDs()
        for (instruction in input) {
            uneditedFile += transMapIDs.instructions[instruction.name]
            when (instruction.name) {
                "lit" -> {
                    uneditedFile += transMapIDs.registers[instruction.values[0]]
                    uneditedFile += instruction.values[1]
                }
            }
        }
        return uneditedFile
    }
}