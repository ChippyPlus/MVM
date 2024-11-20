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

		fun preposeLink(requester: KProcess, acceptent: KProcess): Int? {
			if (requester !in pending) {
				pending[requester] = mutableSetOf()
			}
			pending[requester]!!.add(acceptent)
			if (pending[acceptent]?.contains(requester) == true) {
				idCount++
				mailBoxes[idCount] = Stack()
				requester.ipcPermissions.add(idCount)
				acceptent.ipcPermissions.add(idCount)

				return idCount
			} else {
				return null
			}
		}

		fun send(host: KProcess, id: Int, message: Long): Boolean {
			if (id !in host.ipcPermissions) return false

			mailBoxes[id]!!.push(message)
			return true
		}


		fun receive(host: KProcess, id: Int): Long? {
			if (id !in host.ipcPermissions) return null
			return mailBoxes[id]!!.pop()
		}

	}


}


fun main() {
	val p1 = KProcess(vm = Vm())
	val p2 = KProcess(vm = Vm())
	val p3 = KProcess(vm = Vm())
	val ipc = Ipc.MessagePassing()

	ipc.preposeLink(p1, p2)
	val o = ipc.preposeLink(p1, p1)!!

	ipc.send(p1, 29, 24)
	println(ipc.receive(p1, 29))


}