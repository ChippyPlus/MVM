package environment.interuptManager

import data.registers.RegisterType
import data.registers.write
import errors
import helpers.toLong
import sun.misc.Signal
import sun.misc.Signal.handle
import vm
import kotlin.system.exitProcess

class InterruptManager {

	private fun handle(name: String, jumpWhere: Long) {
		handle(Signal(name)) {
			vm.pc = jumpWhere - 1
			RegisterType.I9.write(true.toLong())
		}
	}

	fun handleSystemCallRequest(code: Int, jumpWhere: Long) {
		var did = false
		Signals.entries.forEach {
			if (it.code.equals(code)) handle(it.signalName, jumpWhere);did = true;return@forEach
		}
		if (!did) {
			errors.SystemCallGeneralException("handleSignals")
		}

	}
}


fun main() {


	println(ProcessHandle.current().pid())

	handle(Signal("INFO")) {
		println("Oh no received a signal!!!!")
		exitProcess(0)
	}


	while (true) {
		Thread.sleep(1000)
	}
}
