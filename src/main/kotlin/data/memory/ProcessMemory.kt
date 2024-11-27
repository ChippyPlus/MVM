package data.memory

import data.stack.FixedStack

class ProcessMemory {
	val heap = Heap()
	val stack = FixedStack()
}