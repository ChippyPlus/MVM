package debugger

import MEMORY_LIMIT
import data.memory.MemoryAddress
import debugger.encoding.EachInstruction
import errors
import fileDescriptors
import internalMemory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import registers
import vm
import java.io.File
import java.nio.file.Paths

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
	fun registersD(mode: DebugInstructionModes) {
		val data = mutableMapOf<String, Long?>()

		for (i in registers.registers) {
			data[i.key.toString()] = i.value
		}

		val location = when (mode) {
			DebugInstructionModes.Iterator -> "each"
			DebugInstructionModes.Line -> "lineSpecific"
		}
		val root = Paths.get("").toAbsolutePath().toString()
		val f = File("$root/debug/$location/registers/frame=${vm.pc}.json")
		f.createNewFile()
		f.writeText(
			json.encodeToString(EachInstruction(vm.pc.toString(), "registers", data))
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
			memMap[address.toString()] = internalMemory.memory[MemoryAddress(address)]?.value
		}
		val location = when (mode) {
			DebugInstructionModes.Iterator -> "each"
			DebugInstructionModes.Line -> "lineSpecific"
		}
		val root = Paths.get("").toAbsolutePath().toString()
		File("$root/debug/$location/memoryRange/frame=${vm.pc}.json").writeText(
			json.encodeToString(EachInstruction(vm.pc.toString(), "memoryRange", memMap))
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
		val root = Paths.get("").toAbsolutePath().toString()
		val f = File("$root/debug/$location/descriptors/frame=${vm.pc}.json")
		f.createNewFile()
		f.writeText(
			json.encodeToString(EachInstruction(vm.pc.toString(), "descriptors", data))
		)
	}

	/**
	 * Captures the value at the top of the stack and writes it to a JSON file.
	 *
	 * @param mode The execution mode, determining the output directory (Line or Iterator).
	 */
	fun stack(mode: DebugInstructionModes) {
		val data = mapOf("current" to vm.stackOperations.internalStack.inspect()[0])
		val location = when (mode) {
			DebugInstructionModes.Iterator -> "each"
			DebugInstructionModes.Line -> "lineSpecific"
		}
		val root = Paths.get("").toAbsolutePath().toString()
		File("$root/debug/$location/stack/frame=${vm.pc}.json").writeText(
			json.encodeToString(EachInstruction(vm.pc.toString(), "stack", data))
		)
	}
}