package internals.systemCalls.calls

import data.registers.RegisterType
import data.registers.read
import environment.reflection.reflection
import internals.Vm
import os
import os_package.Ipc
import kotlin.system.exitProcess

class Ipc(val vm: Vm) {
	val ipcBackBone = Ipc()

	fun link(p1IdRaw: RegisterType, p2IdRaw: RegisterType) {
		val vms = reflection.groupTrackedVmById()
		val p1 = vms[p1IdRaw.read(vm).toInt()] ?: run {
			vm.errors.ProcessNotFound(
				p1IdRaw.read(vm).toString()
			);exitProcess(0)
		}
		val p2 = vms[p2IdRaw.read(vm).toInt()] ?: run {
			vm.errors.ProcessNotFound(
				p2IdRaw.read(vm).toString()
			);exitProcess(0)
		}
		os.ipc.messagePassing.link(p1, p2)
	}

	fun send(p1IdRaw: RegisterType, p2IdRaw: RegisterType, message: RegisterType) {
		val vms = reflection.groupTrackedVmById()
		val p1 = vms[p1IdRaw.read(vm).toInt()] ?: run {
			vm.errors.ProcessNotFound(
				p1IdRaw.read(vm).toString()
			);exitProcess(0)
		}
		val p2 = vms[p2IdRaw.read(vm).toInt()] ?: run {
			vm.errors.ProcessNotFound(
				p2IdRaw.read(vm).toString()
			);exitProcess(0)
		}
		os.ipc.messagePassing.send(p1, p2, message.read(vm))
	}

	fun receive(p1IdRaw: RegisterType, p2IdRaw: RegisterType, message: RegisterType) {
		val vms = reflection.groupTrackedVmById()
		val p1 = vms[p1IdRaw.read(vm).toInt()] ?: run {
			vm.errors.ProcessNotFound(
				p1IdRaw.read(vm).toString()
			);exitProcess(0)
		}
		val p2 = vms[p2IdRaw.read(vm).toInt()] ?: run {
			vm.errors.ProcessNotFound(
				p2IdRaw.read(vm).toString()
			);exitProcess(0)
		}
		os.ipc.messagePassing.revive(p1, p2)
	}
}