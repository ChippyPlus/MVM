package org.example.debuger.internals

import org.example.debuger.jsonInfo.DebugFile
import org.example.kvm
import java.io.File

class DebugEngine(val debugFile: DebugFile) {
    private val debugInstructions = DebugInstructions()
    private val eachInteractionInstruction = debugFile.eachIteration
    private var passedPC = 0

    init {
        val root = "src/main/resources/debug/"
        val filePaths = arrayOf(
            "out",
            "out/each",
            "out/each/memoryRange",
            "out/each/registers",
            "out/lineSpecific",

        )
        for (file in filePaths) {
            if (File(file).exists().not()) {
                File(file).mkdir()
            } else {
                File(file).delete()
                File(file).mkdir()
            }
        }
    }

    fun checkPC() {
        if (kvm.pc != passedPC) {
            passedPC = kvm.pc
        }
    }
    fun eachInteraction() {
        for (instruction in eachInteractionInstruction) {
            val internalInstruction = instruction.split(" ")
            when (internalInstruction[0]) {
                "registers" -> debugInstructions.registers(internalInstruction[1])
                "memoryRange" -> debugInstructions.memoryRange(internalInstruction[1].toInt(), internalInstruction[2].toInt())
            }
        }
    }
    fun invoke(): Int {
        return kvm.pc
    }
}