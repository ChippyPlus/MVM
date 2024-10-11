package kilb

import data.registers.enumIdenifiers.SuperRegisterType
import helpers.fullRegisterRead

class Arrays {
	/**
	 * Like F1 = arrayRef
	 */
	fun size() {
		val ref = fullRegisterRead(SuperRegisterType.F1)

	}
}