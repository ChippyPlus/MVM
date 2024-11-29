package kernel

import config
import data.memory.EMPTY_MEM_VAL
import data.memory.Heap.AllocatedBlock

class OS {
	val mainMemory = LongArray((config.memorySize - config.stackSize).toInt()) { EMPTY_MEM_VAL }
	val usedMemory = mutableListOf<AllocatedBlock>()
	val taskManager = TaskManagerV2()
	val ipc = Ipc()
}