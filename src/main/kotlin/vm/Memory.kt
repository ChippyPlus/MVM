package vm

import config
import os.MemoryBlock
import os.OS
import processes.Pcb
import vm.exceptions.VmExceptions.MemoryAccessException
import java.nio.ByteBuffer
import java.nio.ByteOrder


class Memory(private val initialMemorySize: Int = config.initMemorySize) { // Pass in initial memory size

	var memory: ByteArray = ByteArray(initialMemorySize) // Internal memory representation
	val os: OS = OS()

	private val allocationPointers = mutableMapOf<Int, Int>() // <Pointer,Size>


	fun allocate(size: Int, pcb: Pcb): Int {
		val newLimit = pcb.limitRegister + size

		if (pcb.limitRegister == 0) { // First allocation for this process
			val startAddress = findFreeBlock(size)
			if (startAddress + size > memory.size) {
				growMemory(startAddress + size - memory.size)
			}
			pcb.baseRegister = startAddress
			pcb.limitRegister = size
			allocationPointers[0] = size
			return 0 // First logical address in the new segment
		} else { // Extending the existing segment
			// Try to extend the current segment.
			// If impossible, copy to a new location.
			val newStart = findFreeBlock(newLimit)
			if (newStart != pcb.baseRegister || newLimit > memory.size) { // Need to move/resize
				if (newLimit > memory.size) {
					growMemory(newLimit - memory.size)
				}
				// Copy to the new location
				System.arraycopy(memory, pcb.baseRegister, memory, newStart, pcb.limitRegister.toInt())
				os.freeList.add(MemoryBlock(pcb.baseRegister, pcb.limitRegister))
				pcb.baseRegister = newStart
				os.mergeFreeList()

			}
			val oldLimit = pcb.limitRegister.toInt() // Store the old limit
			pcb.limitRegister += size                // Extend the limit
			allocationPointers[oldLimit] = size
			return oldLimit
		}
	}

	fun deallocate(pcb: Pcb) {
		val start = pcb.baseRegister
		val limit = pcb.limitRegister

		if (start != 0 && limit != 0) { // Only deallocate if a segment exists
			for (i in start until start + limit) {
				memory[i] = 0 // Or add to the free list
			}
		}
		pcb.baseRegister = 0
		pcb.limitRegister = 0

	}


	fun findFreeBlock(size: Int): Int = os.findFreeMemory(size)


	operator fun get(address: Int, pcb: Pcb): Byte {
		val physicalAddress = translateAddress(address, pcb)
		return memory[physicalAddress]
	}

	operator fun set(address: Int, pcb: Pcb, value: Byte) {
		val physicalAddress = translateAddress(address, pcb)
		memory[physicalAddress] = value
	}


	fun translateAddress(logicalAddress: Int, pcb: Pcb): Int {
		val base = pcb.baseRegister
		val limit = pcb.limitRegister

		if (logicalAddress < 0 || logicalAddress >= limit) {
			throw MemoryAccessException("Logical address out of bounds: \"$logicalAddress\", limit (0, ${limit - 1}), for Process: ${pcb.pid}")
		}
		return base + logicalAddress
	}

	fun writeIntToMemory(address: Int, word1: Int, word2: Int, pcb: Pcb) {
		val translatedAddress = translateAddress(address - pcb.baseRegister, pcb)
		val buffer = ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putInt(word1).putInt(word2)
		val byteArray = buffer.array()

		System.arraycopy(byteArray, 0, memory, translatedAddress.toInt(), byteArray.size)
	}

	// Helper function to grow the memory if needed.
	private fun growMemory(additionalSize: Int) {
		val newMemory = ByteArray((initialMemorySize + additionalSize))
		System.arraycopy(memory, 0, newMemory, 0, memory.size)
		memory = newMemory
		// Update any necessary OS structures
		os.resizeMemory(initialMemorySize + additionalSize)


	}
}
