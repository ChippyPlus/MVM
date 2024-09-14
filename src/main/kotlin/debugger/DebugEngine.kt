package debugger

import org.example.internals.encoding.DebugFile
import org.example.kvm
import java.io.File
import java.util.*

class DebugEngine(val debugFile: DebugFile) {
    private val debugInstructions = DebugInstructions()
    private val eachInteractionInstruction = debugFile.eachIteration
    private val linesStack = Stack<Int>()
    private val orderedLineInstructions: MutableList<Int> = emptyList<Int>().toMutableList()

    init {
        val root = "src/main/resources/debug/"
        val filePaths = arrayOf(
            "out",
            "out/each",
            "out/each/memoryRange",
            "out/each/registers",
            "out/lineSpecific",
            "out/lineSpecific/memoryRange",
            "out/lineSpecific/registers",
            "out/lineSpecific/stack",
            "out/each/stack",
            "out/lineSpecific/descriptors",
            "out/each/descriptors",
        )
        for (_f in filePaths) {
            val file = File("$root/$_f")
            if (file.exists().not()) {
                file.mkdir()
            } else {
                file.deleteRecursively()
                file.mkdir()
            }
        }


        debugFile.lineSpecific.keys.forEach { orderedLineInstructions.add(it.toInt()) }
        orderedLineInstructions.sortDescending()
        for (i in orderedLineInstructions) {
            linesStack.push(i)
        }
    }


    fun lineSpecific() {
        if (linesStack.isEmpty()) {
            return
        }
        if (linesStack.peek() == kvm.pc) {
            val internalInstruction = debugFile.lineSpecific[linesStack.pop().toString()]!!.split(" ")
            execute(internalInstruction, DebugInstructionModes.Line)
        }
    }


    fun eachInteraction() {
        for (instruction in eachInteractionInstruction) {
            val internalInstruction = instruction.split(" ")
            execute(internalInstruction, DebugInstructionModes.Iterator)
        }
    }

    fun execute(internalInstruction: List<String>, mode: DebugInstructionModes) {
        when (internalInstruction[0]) {
            "registers" -> debugInstructions.registers(mode)
            "memoryRange" -> debugInstructions.memoryRange(
                internalInstruction[1].toLong(),
                internalInstruction[2].toLong(), mode
            )

            "stack" -> debugInstructions.stack(mode)
            "descriptors" -> debugInstructions.descriptors(mode)
        }
    }
}