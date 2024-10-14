package environment.libEx

import data.registers.enumIdenifiers.SuperRegisterType
import environment.ExecuteLib
import helpers.registerWriteUnsafe

fun ExecuteLib.populateSnapShot(snapShotRegisters: MutableMap<SuperRegisterType, Long?>) {
	for (i in snapShotRegisters) {
		registerWriteUnsafe(i.key, i.value)
	}
}