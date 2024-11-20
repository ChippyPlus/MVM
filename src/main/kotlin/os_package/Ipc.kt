package os_package

import environment.reflection.KProcess
import internals.Vm
import java.util.*


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


	class MessagePassing {
		val pending = mutableMapOf<KProcess, MutableSet<KProcess>>()  // requester,acceptent"S"
		var idCount = 0

		val mailBoxes = mutableMapOf<Int, Stack<Long>>()

		fun isLinked(requester: KProcess, acceptent: KProcess): Boolean {
			return pending[acceptent]?.contains(requester) == true
		}

		fun preposeLink(requester: KProcess, acceptent: KProcess): Int? {
			if (requester !in pending) {
				pending[requester] = mutableSetOf()
			}
			pending[requester]!!.add(acceptent)
			if (isLinked(requester, acceptent)) {
				mailBoxes[idCount] = Stack()
				idCount++
				return idCount
			} else {
				return null
			}

		}

		fun send(host: KProcess, id: Int, message: Long) {

			if (true) {
				mailBoxes[id]!!.push(message)
			}
		}

	}


}


fun main() {
	val p1 = KProcess(vm = Vm())
	val p2 = KProcess(vm = Vm())
	val p3 = KProcess(vm = Vm())
	val ipc = Ipc.MessagePassing()

	println("Requests [p1 -> p2] | ${ipc.preposeLink(p1, p2)}")
	println("Requests [p1 -> p2] | ${ipc.preposeLink(p1, p2)}")
	println("Requests [p1 -> p2] | ${ipc.preposeLink(p1, p2)}")
	println("Requests [p2 -> p1] | ${ipc.preposeLink(p2, p1)}")
	println("Requests [p1 -> p3] | ${ipc.preposeLink(p1, p3)}")
	println("Requests [p3 -> p1] | ${ipc.preposeLink(p3, p1)}")


}