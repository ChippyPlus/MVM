package debugger

import debugger.encoding.DebugFile
import kvm
import java.io.File
import java.nio.file.Paths
import java.util.*

/**
 * The debug engine responsible for executing debug instructions and generating debug output.
 *
 * @property debugFile The [DebugFile] containing the debugger configuration.
 */
class DebugEngine(val debugFile: DebugFile) {
    private val debugInstructions = DebugInstructions()
    private val eachInteractionInstruction = debugFile.eachIteration
    private val linesStack = Stack<Int>()
    private val orderedLineInstructions: MutableList<Int> = emptyList<Int>().toMutableList()

    init {
        val root = Paths.get("").toAbsolutePath().toString()
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
            val file = File("$root/debug/$_f")
            if (file.exists().not()) {
                file.mkdir()
            } else {
                file.deleteRecursively()
                file.mkdir()
            }
        }

        // Initialize the stack for line-specific debug instructions
        debugFile.lineSpecific.keys.forEach { orderedLineInstructions.add(it.toInt()) }
        orderedLineInstructions.sortDescending()
        for (i in orderedLineInstructions) {
            linesStack.push(i)
        }
    }

    /**
     * Executes debug instructions specified for the current line (if any).
     *
     * Checks if there are line-specific debug instructions for the current program counter (PC) value.
     * If found, it pops the instruction from the stack and executes it.
     */
    fun lineSpecific() {
        if (linesStack.isEmpty()) {
            return
        }
        if (linesStack.peek() == kvm.pc) {
            val internalInstruction = debugFile.lineSpecific[linesStack.pop().toString()]!!.split(" ")
            execute(internalInstruction, DebugInstructionModes.Line)
        }
    }

    /**
     * Executes debug instructions specified for each iteration of the VM's execution loop.
     *
     * Iterates through the `eachIteration` instructions from the `debugFile` and executes them.
     */
    fun eachInteraction() {
        for (instruction in eachInteractionInstruction) {
            val internalInstruction = instruction.split(" ")
            execute(internalInstruction, DebugInstructionModes.Iterator)
        }
    }

    /**
     * Executes a single debug instruction.
     *
     * @param internalInstruction The debug instruction, split into parts (command and arguments).
     * @param mode The execution mode ([DebugInstructionModes.Line] or [DebugInstructionModes.Iterator]).
     */
    fun execute(internalInstruction: List<String>, mode: DebugInstructionModes) {
        when (internalInstruction[0]) {
            "registers" -> debugInstructions.registers(mode)
            "memoryRange" -> debugInstructions.memoryRange(
                internalInstruction[1].toLong(), internalInstruction[2].toLong(), mode
            )

            "stack" -> debugInstructions.stack(mode)
            "descriptors" -> debugInstructions.descriptors(mode)
        }
    }
}