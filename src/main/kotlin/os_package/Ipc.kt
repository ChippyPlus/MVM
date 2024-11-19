package os_package

import environment.reflection.KProcess
import java.util.*
import kotlin.math.max
import kotlin.math.min


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
		data class RelationShip(
			val p1: KProcess,
			val p2: KProcess,
			val type: IpcType,
			val mailbox: Stack<Long> = Stack(),
//		val name: String = "${max(p1.id, p2.id)}-${min(p1.id, p2.id)}",
		)

		val mailBoxes = mutableMapOf<Pair<Int, Int>, RelationShip>()
		val pendingLinks = mutableListOf<Pair<Int, Int>>()


		fun arrangeKProcess(p1: KProcess, p2: KProcess) = Pair(max(p1.id, p2.id), (min(p1.id, p2.id)))

		val listener = null // !!! Not Implemented !!!

		fun isLinked(p1: KProcess, p2: KProcess): Boolean {
			val relationShipRequest = Pair(max(p1.id, p2.id), (min(p1.id, p2.id)))
			if (relationShipRequest in pendingLinks) return false
			if (relationShipRequest !in mailBoxes) return false
			return true
		}


		fun preposeLink(p1: KProcess, p2: KProcess): Boolean {
			val relationShipRequest = Pair(max(p1.id, p2.id), (min(p1.id, p2.id)))
			if (relationShipRequest in pendingLinks) {
				pendingLinks.remove(relationShipRequest)
				return true
			}
			pendingLinks.add(relationShipRequest)
			return false
		}


		fun link(p1: KProcess, p2: KProcess) {
			val relationShip = RelationShip(p1, p2, IpcType.MailBox)
			mailBoxes[arrangeKProcess(p1, p2)] = relationShip
		}

		fun send(senderVm: KProcess, receiverVm: KProcess, message: Long): Unit? {
			val key = arrangeKProcess(senderVm, receiverVm)
			mailBoxes[key]?.mailbox?.push(message) ?: return null
			return Unit
		}

		fun revive(senderVm: KProcess, receiverVm: KProcess): Long? {
			val key = arrangeKProcess(senderVm, receiverVm)
			return mailBoxes[key]?.mailbox?.pop()
		}
	}


}