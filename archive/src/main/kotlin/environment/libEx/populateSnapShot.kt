package environment.libEx

import data.registers.RegisterType
import environment.ExecuteLib
import registers

fun ExecuteLib.populateSnapShot(snapShotRegisters: MutableMap<RegisterType, Long?>) {
	for (i in snapShotRegisters) {
		registers.writeUnsafe(i.key, i.value)
	}
}