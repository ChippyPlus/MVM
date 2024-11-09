package environment.interuptManager

import sun.misc.Signal
import sun.misc.Signal.handle
import kotlin.system.exitProcess

class InterruptManager


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
