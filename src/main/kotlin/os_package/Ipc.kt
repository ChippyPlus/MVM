package os_package

import environment.reflection.KProcess
import internals.Vm


/**
 * # Goals.
 * ## Syscalls
 * - Receive,
 * - Send,
 * - Listen,
 */

enum class IpcType {
	Listen, MailBox
}

class Ipc {
	val sharedMemory = SharedMemory()
	val messagePassing = MessagePassing()


	class SharedMemory {
		fun create() {}

		fun attach() {}

	}


	class MessagePassing
}

fun main() {
	val p1 = KProcess(vm = Vm())
	val p2 = KProcess(vm = Vm())


}