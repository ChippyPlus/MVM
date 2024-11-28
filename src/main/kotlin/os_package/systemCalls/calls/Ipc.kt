package os_package.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import data.registers.write
import environment.reflection.reflection
import internals.Vm
import os

class Ipc(val vm: Vm) {
	fun link(p1IdRaw: RegisterType, p2IdRaw: RegisterType) {
		val vms = reflection.groupTrackedVmById()
		val p1 = vms[p1IdRaw.read(vm).toInt()]!!
		val p2 = vms[p2IdRaw.read(vm).toInt()]!!
		val status = os.ipc.messagePassing.preposeLink(p1, p2)

		if (status == null) {
			RegisterType.R2.write(vm, -1)
		} else {
			RegisterType.R2.write(vm, status.toLong())
		}
	}

	fun send(relationShipStatus: RegisterType, message: RegisterType) {
		val vms = reflection.groupTrackedVmByVm()
		os.ipc.messagePassing.send(vms[vm]!!, relationShipStatus.read(vm).toInt(), message.read(vm))
	}

	fun receive(id: RegisterType) {
		RegisterType.R2.write(
			vm, os.ipc.messagePassing.receive(reflection.groupTrackedVmByVm()[vm]!!, id.read(vm).toInt())!!
		)
	}
}