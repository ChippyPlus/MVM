package vm

import config
import os.OS
import processes.Pcb
import vm.exceptions.VmExceptions.MemoryAccessException


class Memory(private val initialMemorySize: Int = config.initMemorySize) { // Pass in initial memory size

	private var memory: ByteArray = ByteArray(initialMemorySize) // Internal memory representation
	private val os: OS = OS()


	fun allocate(size: Int, pcb: Pcb): Int {
		val startAddress = findFreeBlock(size) // Implement findFreeBlock to use heap management
		val endAddress = startAddress + size

		if (endAddress > initialMemorySize) {
			growMemory(endAddress - initialMemorySize)
		}

		pcb.baseRegister = startAddress
		pcb.limitRegister = size
		return startAddress // Returns the start address of the newly allocated segment
	}

	fun deallocate(pcb: Pcb) { // No need for size, use base/limit from PCB

		for (i in pcb.baseRegister until pcb.baseRegister + pcb.limitRegister) {
			memory[i.toInt()] = 0 // Or handle deallocation differently (e.g., return to free list)
		}

	}


	fun findFreeBlock(size: Int): Int = os.findFreeMemory(size)


	operator fun get(address: Int, pcb: Pcb): Byte {
		val physicalAddress = translateAddress(address, pcb)
		return memory[physicalAddress.toInt()]
	}

	operator fun set(address: Int, pcb: Pcb, value: Byte) {
		val physicalAddress = translateAddress(address, pcb)
		memory[physicalAddress.toInt()] = value
	}


	fun translateAddress(logicalAddress: Int, pcb: Pcb): Int {
		val base = pcb.baseRegister
		val limit = pcb.limitRegister

		if (logicalAddress < 0 || logicalAddress >= limit) {
			throw MemoryAccessException("Logical address out of bounds: $logicalAddress  (0, $limit), for Process: ${pcb.pid}")
		}
		return base + logicalAddress
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
