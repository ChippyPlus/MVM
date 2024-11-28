package os_package

import config

class OS {
	val mainMemory = LongArray((config.memorySize - config.stackSize).toInt()) { 0L }
	val taskManager = TaskManagerV2()
	val ipc = Ipc()
}