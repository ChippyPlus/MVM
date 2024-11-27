package data.memory

import config
import internals.Vm
import os_package.KProcess
import java.io.File


class Heap {
	private val m = LongArray((config.memorySize - config.stackSize).toInt()) { 0L }

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
				used += AllocatedBlock(range = start..<start + size, size = size) // Add allocated range to used
				for (j in 0 until size) {
					m[j + start.toInt()] = -1L
				}
				return start
			}
		}
		throw IllegalStateException("Out of memory")
	}


	fun dealloc(address: Long) {
		val rangeToRemove = used.find { address in it.range }
		if (rangeToRemove?.range != null) {
			used.remove(rangeToRemove)
			for (i in rangeToRemove.range) {
				m[i.toInt()] = 0L
			}
		} else {
			throw IllegalArgumentException("Invalid address to deallocate")
		}
	}


	fun get(address: Long, offset: Int): Long {
		val block = used.find { address in it.range }
		if (block != null && offset >= 0 && offset < block.size) {
			val actualAddress = address + offset
			if (actualAddress < 0 && actualAddress >= m.size) throw IllegalStateException("Invalid memory address \"$actualAddress\"")
			return m[actualAddress.toInt()]
		} else {
			throw IllegalArgumentException("Invalid address or offset")
		}
	}


	fun set(address: Long, offset: Int, value: Long) {
		val block = used.find { address in it.range }
		if (block != null && offset >= 0 && offset < block.size) {
			val actualAddress = address + offset
			if (actualAddress < 0 && actualAddress >= m.size) throw IllegalStateException("Invalid memory address \"$actualAddress\"")
			m[actualAddress.toInt()] = value
		} else {
			throw IllegalArgumentException("Invalid address or offset")
		}
	}
}


fun main() {

	val h = Heap(KProcess(Vm(), File("")))

	val addr = h.alloc(2)
	h.set(addr, 0, 111)
	println(h.get(addr, 0))
	h.set(addr, 1, 222)
	println(h.get(addr, 1))
	h.set(addr, 2, 333)
	println(h.get(addr, 2))
	h.set(addr, 3, 444)
	println(h.get(addr, 3))
	h.dealloc(addr)
}