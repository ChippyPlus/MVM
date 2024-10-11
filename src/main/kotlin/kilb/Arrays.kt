package kilb

import data.registers.enumIdenifiers.SuperRegisterType
import helpers.registerRead

class Arrays {
	/**
	 * Like F1 = arrayRef
	 */
	fun size() {
		val ref = registerRead(SuperRegisterType.F1)

	}
}