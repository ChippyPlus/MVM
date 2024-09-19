package debugger

import MEMORY_LIMIT
import data.memory.MemoryAddress
import debugger.encoding.EachInstruction
import errors
import fileDescriptors
import generalRegisters
import internalMemory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kvm
import returnRegisters
import systemRegisters
import java.io.File

/**
 * Defines and executes actions for debug instructions.
 *
 * This class provides functions for capturing and writing debug information,
 * such as register values, memory ranges, stack contents, and file descriptors.
 */
class DebugInstructions {
    private val json = Json { prettyPrint = true }

    /**
     * Captures the values of all registers and writes them to a JSON file.
     *
     * @param mode The execution mode, determining the output directory (Line or Iterator).
     */
    fun registers(mode: DebugInstructionModes) {
        val data = mapOf(
            "G1" to generalRegisters.g1,
            "G2" to generalRegisters.g2,
            "G3" to generalRegisters.g3,
            "G4" to generalRegisters.g4,
            "S1" to systemRegisters.s1,
            "S2" to systemRegisters.s2,
            "S3" to systemRegisters.s3,
            "S4" to systemRegisters.s4,
            "R1" to returnRegisters.r1,
            "R2" to returnRegisters.r2,
            "R3" to returnRegisters.r3,
            "R4" to returnRegisters.r4
        )
        val location = when (mode) {
            DebugInstructionModes.Iterator -> "each"
            DebugInstructionModes.Line -> "lineSpecific"
        }
        @Suppress("UNCHECKED_CAST")
        File("src/main/resources/debug/out/$location/registers/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "registers", data as Map<String,Long?>))
        )
    }

    /**
     * Captures the values of a specified memory range and writes them to a JSON file.
     *
     * @param a The starting address of the memory range.
     * @param b The ending address of the memory range.
     * @param mode The execution mode, determining the output directory (Line or Iterator).
     * @throws MemoryAllocationException If the end address is out of the allocated memory bounds.
     */
    fun memoryRange(a: Long, b: Long, mode: DebugInstructionModes) {
        val memMap = emptyMap<String, Long?>().toMutableMap()
        if (MEMORY_LIMIT <= b) {
            errors.MemoryAllocationException("Debugger/memoryRange is accessing non-existent memory \"$b\"")
        }
        for (address in a..b) {
            memMap[address.toString()] = internalMemory.memory[MemoryAddress(address)]?.value as Long?
        }
        val location = when (mode) {
            DebugInstructionModes.Iterator -> "each"
            DebugInstructionModes.Line -> "lineSpecific"
        }

        File("src/main/resources/debug/out/$location/memoryRange/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "memoryRange", memMap))
        )
    }

    /**
     * Captures the current state of file descriptors and writes them to a JSON file.
     *
     * @param mode The execution mode, determining the output directory (Line or Iterator).
     */
    fun descriptors(mode: DebugInstructionModes) {
        val data = emptyMap<String, Long?>().toMutableMap()
        fileDescriptors.fds.forEach { data[it.key.toString()] = it.key }
        val location = when (mode) {
            DebugInstructionModes.Iterator -> "each"
            DebugInstructionModes.Line -> "lineSpecific"
        }

        File("src/main/resources/debug/out/$location/descriptors/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "descriptors", data))
        )
    }

    /**
     * Captures the value at the top of the stack and writes it to a JSON file.
     *
     * @param mode The execution mode, determining the output directory (Line or Iterator).
     */
    fun stack(mode: DebugInstructionModes) {
        val data = mapOf("current" to kvm.stackOperations.internalStack.inspect()[0])
        val location = when (mode) {
            DebugInstructionModes.Iterator -> "each"
            DebugInstructionModes.Line -> "lineSpecific"
        }

        File("src/main/resources/debug/out/$location/stack/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "stack", data))
        )
    }
}