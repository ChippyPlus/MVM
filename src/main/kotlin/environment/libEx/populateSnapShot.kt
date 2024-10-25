package environment.libEx

import data.registers.RegisterType
import environment.ExecuteLib
import registers

fun ExecuteLib.populateSnapShot(snapShotRegisters: MutableMap<RegisterType, Double?>) {
	for (i in snapShotRegisters) {
		registers.writeUnsafe(i.key, i.value)
	}
}