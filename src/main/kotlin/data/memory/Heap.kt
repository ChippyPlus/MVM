package data.memory

import os
import os_package.KProcess
import kotlin.system.exitProcess


class Heap(val kp: KProcess) {
	var m = os.mainMemory


	data class AllocatedBlock(val range: LongRange, val size: Int)

	private val used = mutableListOf<AllocatedBlock>()

	fun alloc(size: Int): Long {
		var start = -1L
		var count = 0
		for (i in m.indices) {
			if (m[i] == 0L) {
				if (count == 0) start = i.toLong()
				count++

			} else count = 0
			if (count == size) {
				used += AllocatedBlock(range = start..<start + size, size = size)
				for (j in 0 until size) {
					m[j + start.toInt()] = -1L // Alloc longRange
				}
				return start
			}
		}
		kp.vm.errors.MemoryAllocationException(size.toString())
		exitProcess(32)
	}


	fun dealloc(address: Long) {
		val rangeToRemove = used.find { address in it.range }
		if (rangeToRemove?.range != null) {
			used.remove(rangeToRemove)
			for (i in rangeToRemove.range) {
				m[i.toInt()] = 0L
			}
		} else {
			kp.vm.errors.InvalidMemoryAddressException(address)
		}
	}


	fun get(absoluteAddress: Long): Long {
		val block = used.find { absoluteAddress in it.range }

		if (block != null) {
			val offset = (absoluteAddress - block.range.first).toInt() // Calculate offset

			if (offset >= 0 && offset < block.size) {
				return m[absoluteAddress.toInt()]
			} else {
				kp.vm.errors.InvalidOffsetForBlockException(absoluteAddress)
				exitProcess(203)

			}
		} else {
			kp.vm.errors.AccessingUnallocatedMemoryException(absoluteAddress)
			exitProcess(203)
		}


	}

	fun set(absoluteAddress: Long, value: Long) {
		val block = used.find { absoluteAddress in it.range }

		if (block != null) {
			val offset = (absoluteAddress - block.range.first).toInt()
			if (offset >= 0 && offset < block.size) {
				m[absoluteAddress.toInt()] = value
			} else {
				kp.vm.errors.InvalidOffsetForBlockException(absoluteAddress)

			}
		} else {
			kp.vm.errors.InvalidMemoryAddressException(absoluteAddress)

		}


	}
}
