package debugger

import debugger.encoding.EachInstruction
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.*
import org.example.data.memory.MemoryAddress
import java.io.File

class DebugInstructions {
    val json = Json { prettyPrint = true }
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
            DebugInstructionModes.Iterator -> {
                "each"
            }

            DebugInstructionModes.Line -> {
                "lineSpecific"
            }

        }
        File("src/main/resources/debug/out/$location/registers/frame=${kvm.pc}.json").writeText(
            json.encodeToString(
                EachInstruction(
                    kvm.pc.toString(), "registers", data
                )
            )
        )
    }


    fun memoryRange(a: Long, b: Long, mode: DebugInstructionModes) {
        val memMap = emptyMap<String, Long?>().toMutableMap()
        if (MEMORY_LIMIT <= b) {
            errors.MemoryAllocationException("Debugger/memoryRange is accessing non-existent memory \"$b\"")
        }
        for (address in a..b) {
            memMap[address.toString()] = internalMemory.memory[MemoryAddress(address)]?.value
        }
        val location = when (mode) {
            DebugInstructionModes.Iterator -> {
                "each"
            }

            DebugInstructionModes.Line -> {
                "lineSpecific"
            }

        }

        File("src/main/resources/debug/out/$location/memoryRange/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "memoryRange", memMap))
        )
    }

    fun descriptors(mode: DebugInstructionModes) {
        val data = emptyMap<String, Long?>().toMutableMap()
        fileDescriptors.fds.forEach { data[it.key.toString()] = it.key.toLong() }
        val location = when (mode) {
            DebugInstructionModes.Iterator -> {
                "each"
            }

            DebugInstructionModes.Line -> {
                "lineSpecific"
            }

        }

        File("src/main/resources/debug/out/$location/descriptors/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "descriptors", data))
        )
    }

    fun stack(mode: DebugInstructionModes) {

        val data = mapOf<String, Long?>("current" to kvm.stackOperations.internalStack.peek())


        val location = when (mode) {
            DebugInstructionModes.Iterator -> {
                "each"
            }

            DebugInstructionModes.Line -> {
                "lineSpecific"
            }

        }

        File("src/main/resources/debug/out/$location/stack/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "stack", data))
        )
    }
}

