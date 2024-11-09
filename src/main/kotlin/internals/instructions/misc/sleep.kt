package internals.instructions.misc

import data.registers.RegisterType
import data.registers.read

fun Misc.sleep(registerType: RegisterType) {
	Thread.sleep(registerType.read())
}