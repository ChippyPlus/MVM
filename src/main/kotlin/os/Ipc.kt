package os

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

	data class RelationShip(
		val p1: KProcess,
		val p2: KProcess,
		val type: IpcType,
		val mailbox: Stack<Long> = Stack(),
//		val name: String = "${max(p1.id, p2.id)}-${min(p1.id, p2.id)}",
	)


	fun arrangeKProcess(p1: KProcess, p2: KProcess) = "${max(p1.id, p2.id)}-${min(p1.id, p2.id)}"

	val mailBox = mutableMapOf<String, RelationShip>()

	val listener = null // !!! Not Implemented !!!

	fun link(p1: KProcess, p2: KProcess) {
		val relationShip = RelationShip(p1, p2, IpcType.MailBox)
		mailBox[arrangeKProcess(p1, p2)] = relationShip
	}

	fun send(senderVm: KProcess, receiverVm: KProcess, message: Long): Unit? {
		val key = arrangeKProcess(senderVm, receiverVm)
		mailBox[key]?.mailbox?.push(message) ?: return null
		return Unit
	}

	fun revive(senderVm: KProcess, receiverVm: KProcess): Long? {
		val key = arrangeKProcess(senderVm, receiverVm)
		return mailBox[key]?.mailbox?.peek()
	}
}