package internals.systemCalls.calls

import data.registers.RegisterType
import internals.systemCalls.SystemCall
import registers
import vm


fun SystemCall.playF(registerX: RegisterType) {
	val rx = registers.readX(registerX)
	val x = if (rx.isDouble) {
		Double.fromBits(rx.value)
	} else {
		Float.fromBits(rx.value.toInt()).toDouble()
	}
	vm.devices.sound.play(x)
}
