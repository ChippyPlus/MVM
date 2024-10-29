package internals.systemCalls.calls

import data.registers.RegisterType
import internals.systemCalls.SystemCall
import registers
import vm


fun SystemCall.playF(f: RegisterType, d: RegisterType, v: RegisterType) {
	val rx = registers.readX(f)
	val ff = if (rx.isDouble) {
		Double.fromBits(rx.value)
	} else {
		Float.fromBits(rx.value.toInt()).toDouble()
	}
	val vv = if (registers.readX(v).isDouble) {
		Double.fromBits(registers.readX(v).value).toFloat()
	} else {
		Float.fromBits(registers.readX(v).value.toInt())
	}

	val dd = if (registers.readX(d).isDouble) {
		Double.fromBits(registers.readX(d).value)
	} else {
		Float.fromBits(registers.readX(d).value.toInt()).toDouble()
	}



	vm.devices.sound.setVolume(vv)

	vm.devices.sound.play(ff, dd.toFloat())
}
