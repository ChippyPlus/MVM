package org.example.debuger.internals

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.*
import org.example.data.memory.MemoryAddress
import org.example.debuger.internals.encoding.EachInstruction
import java.io.File

class DebugInstructions {
    val json = Json { prettyPrint = true }
    fun registers(which: String) {
        when (which) {
            "ALL" -> {
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

                File("src/main/resources/debug/out/each/registers/frame=${kvm.pc}.json")
                    .writeText(
                    json.encodeToString(
                        EachInstruction(
                            kvm.pc.toString(), "registers", data
                        )
                    )
                )
            }

            else -> println("ERROR WITH REGISTERS")

        }
    }

    fun memoryRange(a: Int, b: Int) {
        val memMap = emptyMap<String, Int?>().toMutableMap()
        for (address in a..b) {
            memMap[address.toString()] = internalMemory.memory[MemoryAddress(address)]!!.value
        }
        File("src/main/resources/debug/out/each/memoryRange/frame=${kvm.pc}.json").writeText(
            json.encodeToString(EachInstruction(kvm.pc.toString(), "memoryRange", memMap))
        )
    }
}