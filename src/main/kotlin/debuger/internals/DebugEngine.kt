package org.example.debuger.internals

import org.example.debuger.jsonInfo.DebugFile
import org.example.kvm

class DebugEngine(val debugFile: DebugFile) {
    private val debugInstructions = DebugInstructions()
    private val eachInteractionInstruction = debugFile.eachIteration
    private var passedPC = 0
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