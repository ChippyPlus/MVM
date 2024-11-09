package environment.interuptManager

import errors
import sun.misc.Signal
import sun.misc.Signal.handle
import kotlin.system.exitProcess

class InterruptManager {

	private fun handle(name: String) {
		handle(Signal(name)) {
			println("Oh no received a signal!!!!")
			exitProcess(0)
		}
	}

	fun requestHandle(signals: Signals) {
		handle(signals.signalName)
	}

	fun handleSystemCallRequest(code: Int) {
		var did = false
		Signals.entries.forEach {
			if (it.code.equals(code)) handle(it.signalName);did = true;return@forEach
		}
		if (!did) {
			errors.SystemCallGeneralException("Signals")
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
