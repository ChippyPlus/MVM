package os

import config
import vm.Memory
import vm.exceptions.VmExceptions.MemoryAllocationException
import java.util.*

data class MemoryBlock(var start: Int, var size: Int, var allocated: Boolean = false)


class OS(initialMemorySize: Int = config.initMemorySize) {
	private val memory = Memory(initialMemorySize) // Memory manager associated with the OS

	private val freeList = LinkedList<MemoryBlock>().apply {
		add(MemoryBlock(0, initialMemorySize))  // All memory initially frees
	}


	fun findFreeMemory(size: Int): Int {
		mergeFreeList() // Merge adjacent free blocks to reduce fragmentation

		val freeBlock = freeList.find { it.size >= size }  // Find the first fit
			?: throw MemoryAllocationException("Not enough free memory")


		val start = freeBlock.start
		if (freeBlock.size > size) {
			freeBlock.start += size
			freeBlock.size -= size
		} else {
			freeList.remove(freeBlock)
		}

		return start
	}

	fun resizeMemory(newSize: Int) {
		// Resize the backing ByteArray in vm/Memory.kt

		// Update the free list (extend the last block or add a new one)
		if (freeList.isNotEmpty()) {
			val lastBlock = freeList.last
			if (!lastBlock.allocated) {
				lastBlock.size = newSize - lastBlock.start
			} else {
				freeList.add(
					MemoryBlock(
						lastBlock.start + lastBlock.size,
						newSize - (lastBlock.start + lastBlock.size)
					)
				)
			}
		} else { // If freeList is empty (edge case), make all memory free
			freeList.add(MemoryBlock(0, newSize))
		}

		// No need to manage Kernel space here, just update total memory.

	}

	private fun mergeFreeList() {
		freeList.sortBy { it.start } // Sort for efficient merging

		val iterator = freeList.iterator()
		var current = if (iterator.hasNext()) iterator.next() else return  // Nothing to merge

		while (iterator.hasNext()) {
			val next = iterator.next()
			if (current.start + current.size == next.start) { // Continuous blocks
				current.size += next.size
				iterator.remove() // Remove the merged block
			} else {
				current = next
			}
		}
	}

}