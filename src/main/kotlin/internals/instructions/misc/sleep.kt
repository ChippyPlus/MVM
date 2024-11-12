package internals.instructions.misc

import data.registers.RegisterType

fun Misc.sleep(registerType: RegisterType) {
	Thread.sleep(vm.registers.read(registerType))
}